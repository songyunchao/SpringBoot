package com.fsun.domain.common;

import java.io.Serializable;

import com.fsun.exception.common.SCMException;
import com.fsun.exception.enums.SCMErrorEnum;

/**
 * http请求json返回封装实体 Created by qyx on 2017-08-07.
 */
public class HttpResult implements Serializable {

	private static final long serialVersionUID = 6398430571891995460L;

	/**
	 * 返回实体
	 */
	protected Object entry;

	/**
	 * 返回状态
	 */
	protected Boolean status;

	/**
	 * 返回状态码
	 */
	protected int code;

	/**
	 * 返回信息
	 */
	protected String message;
	
	/**
	 * token
	 */
	protected String token;

	public HttpResult() {
		this.status = false;
		this.code = SCMErrorEnum.SYSTEM_ERROR.getErrorCode();
		this.message = SCMErrorEnum.SYSTEM_ERROR.getErrorMsg();
	}

	public HttpResult failure(String errorMsg) {
		return this.failure(SCMErrorEnum.SYSTEM_ERROR.getErrorCode(), errorMsg);
	}

	public HttpResult failure(int code, String errorMsg) {
		return this.failure(null, code, errorMsg);
	}

	public HttpResult failure(Object entry, int code, String errorMsg) {
		return this.result(entry, false, code, errorMsg);
	}

	public HttpResult failure(SCMException e) {
		return this.failure(e.getErrorCode(), e.getErrorMsg());
	}

	public HttpResult failure(Object entry, SCMException e) {
		return this.failure(entry, e.getErrorCode(), e.getErrorMsg());
	}

	public HttpResult failure(SCMErrorEnum errorEnum) {
		if (errorEnum == null) {
			errorEnum = SCMErrorEnum.SYSTEM_ERROR;
		}
		return this.failure(errorEnum.getErrorCode(), errorEnum.getErrorMsg());
	}

	public HttpResult failure(Object entry, SCMErrorEnum errorEnum) {
		return this.failure(entry, errorEnum.getErrorCode(),
				errorEnum.getErrorMsg());
	}

	public HttpResult success() {
		return this.success(null);
	}

	public HttpResult success(Object entry) {
		return this.success(entry, SCMErrorEnum.SUCCESS.getErrorCode(),
				SCMErrorEnum.SUCCESS.getErrorMsg());
	}
	
	public HttpResult success(Object entry, String token) {
		return this.success(entry, SCMErrorEnum.SUCCESS.getErrorCode(),
				SCMErrorEnum.SUCCESS.getErrorMsg(), token);
	}


	public HttpResult success(String message) {
		return this.success(null, SCMErrorEnum.SUCCESS.getErrorCode(), message);
	}

	public HttpResult success(int code, String message) {
		return this.success(null, code, message);
	}

	public HttpResult success(Object entry, int code, String message) {
		return this.result(entry, true, code, message);
	}
	
	public HttpResult success(Object entry, int code, String message, String token) {
		return this.result(entry, true, code, message, token);
	}

	private HttpResult result(Object entry, boolean status, int code,
			String message) {
		if (entry != null) {
			this.entry = entry;
		}
		this.status = status;
		this.code = code;
		this.message = message;
		return this;
	}
	
	private HttpResult result(Object entry, boolean status, int code,
			String message, String token) {
		if (entry != null) {
			this.entry = entry;
		}
		this.status = status;
		this.code = code;
		this.message = message;
		this.token = token;
		return this;
	}

	public Boolean getStatus() {
		return status;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getEntry() {
		return entry;
	}

	public String getToken() {
		return token;
	}
	
}
