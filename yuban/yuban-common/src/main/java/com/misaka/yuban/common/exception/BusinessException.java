package com.misaka.yuban.common.exception;

/**
 * 业务正常执行过程中抛出的异常
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
