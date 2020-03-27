package com.scaffold.springboot.mybatis.common.exception;


import lombok.Getter;
import lombok.Setter;

/**
 * @author majian
 */
public class ApiException extends Exception {
    @Getter
    @Setter
    int status;

    public ApiException(ServiceException serviceException) {
        super(serviceException.getMessage(), serviceException);
        ServiceException.ReasonEnum reason = serviceException.getReason();
        status = reason.getCode();
    }

    public ApiException(String message, int status) {
        super(message);
        this.status = status;
    }

    public ApiException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }

    public ApiException(Throwable cause, int status) {
        super(cause);
        this.status = status;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }
}
