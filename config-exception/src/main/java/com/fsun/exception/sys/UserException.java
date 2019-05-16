package com.fsun.exception.sys;

import com.fsun.exception.common.SCMException;
import com.fsun.exception.enums.SCMErrorEnum;

/**
 * 用户类异常
 * @author sunxiaolei
 *
 */
public class UserException extends SCMException{

	private static final long serialVersionUID = -5008891508099752037L;
	
	public UserException(String errorMsg) {
        this(SCMErrorEnum.CUSTOMIZE_ERROR.getErrorCode(), errorMsg);
    }

    public UserException(int errorCode, String errorMsg) {
    	super(errorCode, errorMsg, null);
    }
    
    public UserException(SCMErrorEnum errorEnum) {
        super(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), null);
    }
    
    public UserException(SCMErrorEnum errorEnum, Throwable caused) {
    	super(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), caused);
    }
}
