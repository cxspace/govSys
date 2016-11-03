package com.cx.core.exception;

/**
 * Created by cx on 2016/10/01.
 */
public class ServiceException extends SysException{

    public ServiceException(){
        super("业务操作错误！");
    }

    public ServiceException(String message) {
        super(message);
    }


}
