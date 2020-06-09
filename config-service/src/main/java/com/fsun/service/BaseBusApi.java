package com.fsun.service;

/**
 * 业务模块基类
 * @author fsun
 * @date 2020年6月6日
 * @param <T>
 * @param <C>
 */
public interface BaseBusApi<T, C> extends BaseApi<T, C>{

	/**
	 * 导出
	 * @param condition
	 * @return
	 */
	public String export(C condition) ;
	
}
