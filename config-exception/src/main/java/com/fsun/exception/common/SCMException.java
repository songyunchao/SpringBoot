package com.fsun.exception.common;

/**
 * 公共异常基类
 * @author sunxiaolei
 *
 */
public class SCMException extends RuntimeException {
	
	private static final long serialVersionUID = 8981762142141016513L;
	
	/**
	 * 未知异常
	 */
	public static final Integer CODE_UNKNOW = 999;
	
	/**
	 * 自定义异常
	 */
	public static final Integer CODE_CUSTOMIZE = 998;	
		
	/**
	 * 参数错误
	 */
	public static final Integer CODE_PARAM = 800;
	
	/**
	 * 网络异常
	 */
	public static final Integer CODE_NET = 700;
	
	/**
	 * 业务异常(除增删查改之外的业务异常)
	 */
	public static final Integer CODE_BIZ = 600;
	
	/**
	 * 插入异常
	 */
	public static final Integer CODE_INSERT = 601;
	/**
	 * 更新异常
	 */
	public static final Integer CODE_UPDATE = 602;
	/**
	 * 删除异常
	 */
	public static final Integer CODE_DELETE = 603;
	
	/**
	 * 查询异常
	 */
	public static final Integer CODE_SELECT = 604;
	
	/**
	 * 保存异常
	 */
	public static final Integer CODE_SAVE = 605;

	/**
	 * 异常代码
	 */
    private int errorCode;

    /**
     * 异常信息
     */
    private String errorMsg;
    
    /**
     * 异常链
     */
    private Throwable caused;
    
    
    public SCMException(int errorCode, String errorMsg, Throwable caused) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        if(caused != null){
        	this.caused = caused;
        }
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
    
    public Throwable getCaused() {
		return caused;
	}

	@Override
    public String toString() {
        return "SCMException{" + "errorCode=" + errorCode + ", errorMsg='" + errorMsg + '\'' + '}';
    }
}