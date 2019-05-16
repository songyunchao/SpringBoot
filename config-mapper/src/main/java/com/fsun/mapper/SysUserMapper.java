package com.fsun.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fsun.domain.model.SysUser;
import com.fsun.mapper.common.BaseMySqlMapper;

/**
 * 
 * @ClassName: SysUserMapper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月16日 下午2:56:27 
 *
 */

@Mapper
public interface SysUserMapper extends BaseMySqlMapper<SysUser>{

}