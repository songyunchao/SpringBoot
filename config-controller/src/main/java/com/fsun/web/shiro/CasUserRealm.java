package com.fsun.web.shiro;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.pac4j.cas.profile.CasProfile;
import org.pac4j.cas.profile.CasRestProfile;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.beans.factory.annotation.Autowired;

import com.fsun.domain.condition.SysUserCondition;
import com.fsun.domain.model.SysRole;
import com.fsun.domain.model.SysUser;
import com.fsun.service.SysUserApi;

import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jPrincipal;
import io.buji.pac4j.token.Pac4jToken;

/**
 * 
 * @ClassName: CasUserRealm 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月29日 上午12:16:22 
 *
 */
public class CasUserRealm extends Pac4jRealm {
	
	public static final String SESSION_USER_KEY = "user";
	
	@Autowired
	private SysUserApi userApi;

	public CasUserRealm() {
		setAuthenticationTokenClass(Pac4jToken.class);
	}

	/**
	 * 登录认证
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken authenticationToken)
			throws AuthenticationException {

		List<SysUser> userList = null;
		final Pac4jToken token = (Pac4jToken) authenticationToken;	
		String username = this.getUsernameFromToken(token);		
		Subject subject = SecurityUtils.getSubject();		
		if (null != subject) {
			Object user = SecurityUtils.getSubject().getSession().getAttribute(SESSION_USER_KEY);
			if(user == null){
				SysUserCondition condition = new SysUserCondition();		
				condition.setUsername(username);
				userList = userApi.list(condition);
				if (userList == null || userList.size() != 1) {
					throw new UnknownAccountException();
				}
			}
		}
		//认证操作
		final LinkedHashMap<String, CommonProfile> profiles = token.getProfiles();
		final Pac4jPrincipal principal = new Pac4jPrincipal(profiles, this.getPrincipalNameAttribute());
		final PrincipalCollection principalCollection = new SimplePrincipalCollection(principal, getName());
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principalCollection, profiles.hashCode());
		//当验证都通过后，把用户信息放在session里
		if(userList != null){
			this.setSession(SESSION_USER_KEY, userList.get(0));
		}		
		return authenticationInfo;
	}

	/**
	 * 权限认证
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		final Set<String> roleNames = new HashSet<>();
		final Set<String> permissions = new HashSet<>();
		final Pac4jPrincipal principal = principals.oneByType(Pac4jPrincipal.class);
		if (principal != null) {
			final List<CommonProfile> profiles = principal.getProfiles();
			for (CommonProfile profile : profiles) {
				if (profile != null) {
					//获取当前登录用户的username
					String username = profile.getId();
					System.out.println("current_user is :" + username);					
					List<SysRole> roles = userApi.findRolesByUsername(username);					
					for (SysRole sysRole : roles) {
						roleNames.add(sysRole.getName());
					}
					permissions.addAll(userApi.findPermissionsByUsername(username));					
				}
			}
		}

		final SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(roleNames);
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}
	
	
	/***********************************         私有方法                 ****************************************/
	
	/**
	 * 将用户放到ShiroSession中
	 */
	private void setSession(Object key, Object value) {
		Subject subject = SecurityUtils.getSubject();
		if (null != subject) {
			subject.getSession().setAttribute(key, value);
			System.out.println("Session默认超时时间为["+ subject.getSession().getTimeout() + "]毫秒");
		}
	}
	
	/**
	 * 从token中过去username
	 * @param token
	 * @return
	 */
	private String getUsernameFromToken(Pac4jToken token) {
		String username = null;
		Optional<Object> optional = (Optional<Object>) token.getPrincipal();
		Object obj = optional.get();			
		if(obj instanceof CasRestProfile){
			CasRestProfile casRestProfile = (CasRestProfile)obj;
			username = casRestProfile.getId();
		}else if(obj instanceof CasProfile){
			CasProfile casProfile = (CasProfile)obj;
			username = casProfile.getId();
		}else{
			throw new UnknownAccountException();
		}
		return username;
	}

}

