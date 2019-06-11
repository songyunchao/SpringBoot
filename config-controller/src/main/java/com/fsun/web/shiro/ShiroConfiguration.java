package com.fsun.web.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.cas.profile.CasProfile;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jSubjectFactory;

/**
 * 
 * @ClassName: ShiroConfiguration 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月28日 下午11:46:28 
 *
 */
@Configuration
public class ShiroConfiguration extends AbstractShiroWebFilterConfiguration {
	
    @Value("${cas.prefixUrl}")
    private String prefixUrl;
    
    @Value("${cas.loginUrl}")
    private String casLoginUrl;
    
    @Value("${cas.callbackUrl}")
    private String callbackUrl;

    //jwt秘钥
    @Value("${jwt.salt}")
    private String salt;

    @Bean
    public Realm pac4jRealm() {
        return new Pac4jRealm();
    }
    
    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 设置开启权限校验
     * @param securityManager
     * @return
     */
    @Bean  
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
    		SecurityManager securityManager){  
       AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();  
       advisor.setSecurityManager(securityManager);  
       return advisor;  
    }  
    
    /**
     * 
     * @Title: casSecurityFilter 
     * @Description: cas核心过滤器，把支持的client写上，filter过滤时才会处理，clients必须在casConfig.clients已经注册
     * @param @return 
     * @return Filter
     */
    @Bean
    public Filter casSecurityFilter() {
        SecurityFilter filter = new SecurityFilter();
        filter.setClients("CasClient,rest,jwt");
        filter.setConfig(casConfig());
        return filter;
    }


    /**
     * 
     * @Title: jwtGenerator 
     * @Description: JWT Token 生成器，对CommonProfile生成然后每次携带token访问
     * @param @return 
     * @return JwtGenerator<CasProfile>
     */
    @Bean
    protected JwtGenerator<CasProfile> jwtGenerator() {
        return new JwtGenerator<CasProfile>(new SecretSignatureConfiguration(salt), 
        	new SecretEncryptionConfiguration(salt));
    }


    /**
     * 
     * @Title: casRestFormClient 
     * @Description: 通过rest接口可以获取tgt，获取service ticket，甚至可以获取CasProfile
     * @param @return 
     * @return CasRestFormClient
     */
    @Bean
    protected CasRestFormClient casRestFormClient() {
        CasRestFormClient casRestFormClient = new CasRestFormClient();
        casRestFormClient.setConfiguration(casConfiguration());
        casRestFormClient.setName("rest");
        return casRestFormClient;
    }


    @Bean
    protected Clients clients() {
        
        //token校验器，可以用HeaderClient更安全
        ParameterClient parameterClient = new ParameterClient("token", jwtAuthenticator());
        parameterClient.setSupportGetRequest(true);
        parameterClient.setName("jwt");
        
        //可以设置默认client
        Clients clients = new Clients();
        //支持的client全部设置进去---CasClient, rest, jwt
        clients.setClients(casClient(), casRestFormClient(), parameterClient);
        return clients;
    }

    /**
     * JWT校验器，也就是目前设置的ParameterClient进行的校验器，是rest/或者前后端分离的核心校验器
     * @return
     */
    @Bean
    protected JwtAuthenticator jwtAuthenticator() {
        JwtAuthenticator jwtAuthenticator = new JwtAuthenticator();
        jwtAuthenticator.addSignatureConfiguration(new SecretSignatureConfiguration(salt));
        jwtAuthenticator.addEncryptionConfiguration(new SecretEncryptionConfiguration(salt));
        return jwtAuthenticator;
    }

    @Bean
    protected Config casConfig() {
        Config config = new Config();
        config.setClients(clients());
        return config;
    }

    /**
     * cas的基本设置，包括或url等等，rest调用协议等
     * @return
     */
    @Bean
    public CasConfiguration casConfiguration() {
        CasConfiguration casConfiguration = new CasConfiguration(casLoginUrl);
        casConfiguration.setProtocol(CasProtocol.CAS30);
        casConfiguration.setPrefixUrl(prefixUrl);
        return casConfiguration;
    }

    @Bean
    public CasClient casClient() {
        CasClient casClient = new CasClient();
        casClient.setConfiguration(casConfiguration());
        casClient.setCallbackUrl(callbackUrl);
        //casClient.setName("CasClient");
        return casClient;
    }


    /**
     * 
     * @Title: shiroFilterChainDefinition 
     * @Description: 路径过滤设置
     * @param @return 
     * @return ShiroFilterChainDefinition
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/callback", "callbackFilter");
        definition.addPathDefinition("/logout", "logoutFilter");
        definition.addPathDefinition("/**", "casSecurityFilter");
        return definition;
    }


    /**
     * 
     * @Title: subjectFactory 
     * @Description: 由于cas代理了用户，所以必须通过cas进行创建对象
     * @param @return 
     * @return SubjectFactory
     */
    @Bean
    protected SubjectFactory subjectFactory() {
        return new Pac4jSubjectFactory();
    }
    
    @Bean
    protected Realm realm(){
    	return new CasUserRealm();
    }

    /**
     * 对shiro的过滤策略进行明确
     * @return
     */
    @Bean
    protected Map<String, Filter> filters() {
        //过滤器设置
        Map<String, Filter> filters = new HashMap<>();
        
        ///filters.put("authc",  new CrossDomainFilter());
        
        filters.put("casSecurityFilter", casSecurityFilter());
        
        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(casConfig());
        filters.put("callbackFilter", callbackFilter);
        
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setConfig(casConfig());
        filters.put("logoutFilter", logoutFilter);
        return filters;
    }
    
    /**
     * 对过滤器进行调整
     *
     * @param securityManager
     * @return
     */
    @Bean
    protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = super.shiroFilterFactoryBean();
        filterFactoryBean.setFilters(filters());
        return filterFactoryBean;
    }

}
