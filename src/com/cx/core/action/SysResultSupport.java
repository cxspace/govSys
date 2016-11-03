package com.cx.core.action;

import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;
import org.apache.xmlbeans.impl.xb.xmlschema.BaseAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cx on 2016/10/01.
 */
public class SysResultSupport extends StrutsResultSupport{

    @Override
    protected void doExecute(String s, ActionInvocation invocation) throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        BaseAction action = (BaseAction) invocation.getAction();

        System.out.println("进入了SysResultSupport类");
        //可以做任何操作

    }
}
