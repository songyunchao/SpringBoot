package com.fsun.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fsun.domain.model.SysRole;
import com.fsun.mapper.common.BaseMySqlMapper;

/**
 * 
 * @ClassName: SysRoleMapper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月16日 下午2:56:45 
 *
 */

@Mapper
public interface SysRoleMapper extends BaseMySqlMapper<SysRole>{

	/**
	 * 
	 * @Title: findRolesByUsername 
	 * @Description: 通过用户账号获取对应角色信息
	 * @param @param username
	 * @param @return 
	 * @return List<SysRole>
	 */
	public List<SysRole> findRolesByUsername(String username);

}