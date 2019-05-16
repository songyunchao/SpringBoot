package com.fsun.exception.common;

import com.fsun.exception.enums.SCMErrorEnum;

/**
 * 数据字典异常
 * @author sunxiaolei
 *
 */
public class BasCodesException extends SCMException{

	private static final long serialVersionUID = -5008891508099752037L;
	
	public BasCodesException(String errorMsg) {
        this(SCMErrorEnum.CUSTOMIZE_ERROR.getErrorCode(), errorMsg);
    }

    public BasCodesException(int errorCode, String errorMsg) {
    	super(errorCode, errorMsg, null);
    }
    
    public BasCodesException(SCMErrorEnum errorEnum) {
        super(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), null);
    }
    
    public BasCodesException(SCMErrorEnum errorEnum, Throwable caused) {
    	super(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), caused);
    }
}
