package com.fsun.web.controller.base;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.profile.CasProfile;
import org.pac4j.cas.profile.CasRestProfile;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.jwt.profile.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsun.domain.common.HttpResult;
import com.fsun.domain.condition.SysUserCondition;
import com.fsun.domain.model.SysUser;
import com.fsun.exception.enums.SCMErrorEnum;
import com.fsun.service.SysUserApi;
import org.apache.shiro.mgt.SecurityManager;

@Controller
public class HelloController extends BaseController{
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello(){
		return "redirect:/swagger-ui.html";
	}
	
	@Autowired
	private SysUserApi sysUserApi;
	
	@Autowired
    private JwtGenerator<CasProfile> generator;

    @Autowired
    private CasRestFormClient casRestFormClient;
    
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    
    @Autowired
    private HttpServletRequest request;

    @Value("${cas.serviceUrl}")
    private String serviceUrl;

      
	/**
	 * 
	 * @Title: login 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param condition
	 * @param @return 
	 * @return HttpResult
	 */
    @RequestMapping(value="/user/login", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public HttpResult login(HttpServletRequest request, HttpServletResponse response) {   	   	   	
         J2EContext context = new J2EContext(request, response);
         final ProfileManager<CasRestProfile> manager = new ProfileManager<CasRestProfile>(context);
         //获取tgt
         final Optional<CasRestProfile> profile = manager.get(true);
         //获取ticket
         TokenCredentials tokenCredentials = casRestFormClient.requestServiceTicket(serviceUrl, profile.get(), context);
         //根据ticket获取用户信息
         final CasProfile casProfile = casRestFormClient.validateServiceTicket(serviceUrl, tokenCredentials, context);
         //生成jwt token
         String token = generator.generate(casProfile);
         HashMap<String, Object> resultMap = new HashMap<>();
         resultMap.put("token", token);
         return success(resultMap);
	}
    
    /**
	 * 
	 * @Title: getUserInfo 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param condition
	 * @param @return 
	 * @return HttpResult
	 */
    @RequestMapping(value="/user/info", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult getUserInfo() {
		try {
			Principal principal = request.getUserPrincipal();
    	    String username = principal.getName();
    	    SysUserCondition condition = new SysUserCondition();
    	    condition.setUsername(username);	
			List<SysUser> list = sysUserApi.list(condition);
			if(list!=null && list.size()==1){	
				HashMap<String, Object> result = new HashMap<>();
				result.put("name", list.get(0).getRealname());
				result.put("roles", new String[]{"admin"});
				result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
				result.put("introduction", "123456789");
				return success(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
		return failure(SCMErrorEnum.USER_ILLEGAL);
	}
    
    /**
	 * 
	 * @Title: logout 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param condition
	 * @param @return 
	 * @return HttpResult
	 */
    @RequestMapping(value="/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public HttpResult logout(HttpServletRequest request, HttpServletResponse response) {    	 
    	 SecurityManager securityManager = shiroFilterFactoryBean.getSecurityManager();
    	 Subject subject = SecurityUtils.getSubject();
    	 securityManager.logout(subject);
         return success();
	}

}
