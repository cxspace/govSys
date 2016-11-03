package com.cx.core.exception;

/**
 * Created by cx on 2016/10/01.
 */
public class ActionException extends SysException{
    public ActionException(){
        super("请求发生错误");
    }

    public ActionException(String message) {
        super(message);
    }
}
