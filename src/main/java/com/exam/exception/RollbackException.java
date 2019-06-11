package com.exam.exception;

/**
 * 数据回滚异常
 */
public class RollbackException extends RuntimeException {
    public RollbackException(String message){
        super(message);
    }

    public RollbackException(Throwable e){
        super(e);
    }
}
