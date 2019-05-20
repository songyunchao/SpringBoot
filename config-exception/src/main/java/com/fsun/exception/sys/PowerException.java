package com.fsun.exception.sys;

import com.fsun.exception.common.SCMException;
import com.fsun.exception.enums.SCMErrorEnum;

/**
 * 权限操作异常类
 * @ClassName: PowerException 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月20日 下午1:37:56 
 *
 */
public class PowerException extends SCMException{

	private static final long serialVersionUID = -5008891508099752037L;
	
	public PowerException(String errorMsg) {
        this(SCMErrorEnum.CUSTOMIZE_ERROR.getErrorCode(), errorMsg);
    }

    public PowerException(int errorCode, String errorMsg) {
    	super(errorCode, errorMsg, null);
    }
    
    public PowerException(SCMErrorEnum errorEnum) {
        super(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), null);
    }
    
    public PowerException(SCMErrorEnum errorEnum, Throwable caused) {
    	super(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), caused);
    }
}