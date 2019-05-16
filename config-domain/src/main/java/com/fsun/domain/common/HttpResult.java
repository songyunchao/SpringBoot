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
	protected int responseCode;

	/**
	 * 返回信息
	 */
	protected String message;

	public HttpResult() {
		this.status = false;
		this.responseCode = SCMErrorEnum.SYSTEM_ERROR.getErrorCode();
		this.message = SCMErrorEnum.SYSTEM_ERROR.getErrorMsg();
	}

	public HttpResult failure(String errorMsg) {
		return this.failure(SCMErrorEnum.SYSTEM_ERROR.getErrorCode(), errorMsg);
	}

	public HttpResult failure(int responseCode, String errorMsg) {
		return this.failure(null, responseCode, errorMsg);
	}

	public HttpResult failure(Object entry, int responseCode, String errorMsg) {
		return this.result(entry, false, responseCode, errorMsg);
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

	public HttpResult success(String message) {
		return this.success(null, SCMErrorEnum.SUCCESS.getErrorCode(), message);
	}

	public HttpResult success(int responseCode, String message) {
		return this.success(null, responseCode, message);
	}

	public HttpResult success(Object entry, int responseCode, String message) {
		return this.result(entry, true, responseCode, message);
	}

	private HttpResult result(Object entry, boolean status, int responseCode,
			String message) {
		if (entry != null) {
			this.entry = entry;
		}
		this.status = status;
		this.responseCode = responseCode;
		this.message = message;
		return this;
	}

	public Boolean getStatus() {
		return status;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getMessage() {
		return message;
	}

	public Object getEntry() {
		return entry;
	}
}
