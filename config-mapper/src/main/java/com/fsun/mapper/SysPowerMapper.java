package com.fsun.mapper;


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

}