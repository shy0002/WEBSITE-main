package com.ayang.website.common.exception;

import lombok.Data;

/**
 * @author shy
 * @date 2023/12/20
 * @description 业务异常
 */
@Data
public class BusinessException extends RuntimeException {
    protected Integer errorCode;
    protected String errorMsg;

    public BusinessException(String errorMsg){
        super(errorMsg);
        this.errorCode = CommonErrorEnum.BUSINESS_ERROR.getErrorCode();
        this.errorMsg=errorMsg;
    }

    public BusinessException(Integer errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg=errorMsg;
    }

}
