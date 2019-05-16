package com.fsun.mapper.common;

import java.util.HashMap;
import java.util.List;

/**
 * dao层基类
 * @author sunxiaolei
 *
 * @param <T>
 */
public interface BaseMySqlMapper<T> {
	
	/**
	 * 获取编码规则下的最大单号
	 * @param prefix
	 * @return
	 */
	public List<String> getMaxNumber(String prefix);
	
	public int deleteByPrimaryKey(Object primaryKey);
	
	public int delete(T record);
	
	public int insert(T record);

    public int insertSelective(T record);

    public T selectByPrimaryKey(Object primaryKey);
    
    /**
     * 获取头及明细对象
     * @param primaryKey
     * @return
     */
    public HashMap<String, Object> loadEntity(Object primaryKey);
    
    public <C>List<T> selectList(C condition);
    
    public <C>List<HashMap<String, Object>> selectListMap(C condition);
    
    /**
	 * 获取表单合计行
	 * @param condition
	 * @return
	 */
	public <C>HashMap<String, Object> findFooter(C condition);
	
	/**
	 * 导出excel
	 * @param condition
	 * @return
	 */
	public <C>List<HashMap<String, Object>> export(C condition);

	public int updateByPrimaryKeySelective(T record);

	public int updateByPrimaryKey(T record);

}
