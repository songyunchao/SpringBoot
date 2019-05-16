package com.fsun.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.fsun.domain.model.SysSettings;
import com.fsun.mapper.common.BaseMySqlMapper;

/**
 * 
 * @ClassName: SysSettingsMapper 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月16日 下午2:56:35 
 *
 */

@Mapper
public interface SysSettingsMapper extends BaseMySqlMapper<SysSettings>{

}