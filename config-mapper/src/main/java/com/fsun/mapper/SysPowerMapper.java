package com.fsun.mapper;


import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;

import com.fsun.domain.model.SysPower;
import com.fsun.mapper.common.BaseMySqlMapper;

/**
 * 
 * @ClassName: SysPowerMapper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月16日 下午2:56:54 
 *
 */

@Mapper
public interface SysPowerMapper extends BaseMySqlMapper<SysPower>{

	/**
	 * 
	 * @Title: findPermissionsByUsername 
	 * @Description: 通过用户账号获取对应的权限编码集合
	 * @param @param username
	 * @param @return 
	 * @return Collection<? extends String>
	 */
	public Collection<? extends String> findPermissionsByUsername(String username);

}