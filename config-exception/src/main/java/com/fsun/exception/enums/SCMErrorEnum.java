package com.fsun.exception.enums;

import com.fsun.exception.common.SCMException;

/**
 * 异常代码/信息枚举
 * @author sunxiaolei
 *
 */
public enum SCMErrorEnum {

	//成功信息
	SUCCESS(200, "处理成功"), 
	
	//系统默认异常信息
	INVALID_PARAMS(SCMException.CODE_PARAM, "参数为空或者不完整"), 
	SYSTEM_ERROR(SCMException.CODE_UNKNOW, " 系统处理失败"), 
	CUSTOMIZE_ERROR(SCMException.CODE_CUSTOMIZE, " 自定义错误"),
	
	//用户异常信息-10
	NO_LOGIN(1001, "您还未登陆"), 
	USER_DOES_NOT_EXIST(1002, "用户不存在"),
	USER_ID_NOT_NULL(1003, "用户id不能为空"),
	USER_ILLEGAL(1004, "用户非法");
	
	private int errorCode;
	
	private String errorType;

	private String errorMsg;

	SCMErrorEnum(int errorCode, String errorMsg) {
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @return the errorType
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * @param errorType the errorType to set
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}


}
