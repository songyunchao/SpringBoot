package com.fsun.web.shiro;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.beans.factory.annotation.Autowired;

import com.fsun.domain.model.SysRole;
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
	
	@Autowired
	private SysUserApi userApi;

	public CasUserRealm() {
		setAuthenticationTokenClass(Pac4jToken.class);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken authenticationToken)
			throws AuthenticationException {

		final Pac4jToken token = (Pac4jToken) authenticationToken;
		final LinkedHashMap<String, CommonProfile> profiles = token.getProfiles();

		final Pac4jPrincipal principal = new Pac4jPrincipal(profiles, this.getPrincipalNameAttribute());
		final PrincipalCollection principalCollection = new SimplePrincipalCollection(principal, getName());
		return new SimpleAuthenticationInfo(principalCollection, profiles.hashCode());
	}

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

}

