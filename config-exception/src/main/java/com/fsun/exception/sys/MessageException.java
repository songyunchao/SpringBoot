package com.fsun.exception.sys;

import com.fsun.exception.common.SCMException;
import com.fsun.exception.enums.SCMErrorEnum;

public class MessageException extends SCMException{

	private static final long serialVersionUID = -5008891508099752037L;
	
	public MessageException(String errorMsg) {
        this(SCMErrorEnum.CUSTOMIZE_ERROR.getErrorCode(), errorMsg);
    }

    public MessageException(int errorCode, String errorMsg) {
    	super(errorCode, errorMsg, null);
    }
    
    public MessageException(SCMErrorEnum errorEnum) {
        super(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), null);
    }
    
    public MessageException(SCMErrorEnum errorEnum, Throwable caused) {
    	super(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), caused);
    }

}
