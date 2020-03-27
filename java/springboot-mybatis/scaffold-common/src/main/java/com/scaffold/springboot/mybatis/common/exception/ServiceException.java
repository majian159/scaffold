package com.scaffold.springboot.mybatis.common.exception;

import lombok.Getter;

/**
 * @author majian
 */
public class ServiceException extends Exception {

    @Getter
    private ReasonEnum reason;

    public ServiceException(ReasonEnum reason, String message) {
        this(reason, message, null);
    }

    public ServiceException(ReasonEnum reason, String message, Throwable cause) {
        super(message, cause);
        this.reason = reason;
    }

    public static ServiceException bad(String message) {
        return new ServiceException(ReasonEnum.BadRequest, message);
    }

    public static ServiceException notFound(String message) {
        return new ServiceException(ReasonEnum.NotFound, message);
    }

    public static ServiceException notFound(String messageFormat, Object... args) {
        return notFound(String.format(messageFormat, args));
    }

    public static ServiceException alreadyExists(String message) {
        return new ServiceException(ReasonEnum.AlreadyExists, message);
    }

    public static ServiceException alreadyExists(String messageFormat, Object... args) {
        return alreadyExists(String.format(messageFormat, args));
    }

    public static ServiceException internalError(String message) {
        return new ServiceException(ReasonEnum.InternalError, message);
    }

    public static ServiceException internalError(String messageFormat, Object... args) {
        return new ServiceException(ReasonEnum.InternalError, String.format(messageFormat, args));
    }

    public enum ReasonEnum {
        //400
        BadRequest(400),
        //404
        NotFound(404),
        //409
        AlreadyExists(409),
        //500
        InternalError(500);

        @Getter
        private Integer code;

        ReasonEnum(Integer code) {
            this.code = code;
        }
    }
}
