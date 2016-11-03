package com.cx.login.action;

import com.cx.core.constant.Constant;
import com.cx.nsfw.user.entity.User;
import com.cx.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Created by cx on 2016/10/23.
 */
public class LoginAction extends ActionSupport{


    private User user;

    private  Log log = LogFactory.getLog(getClass());

    @Resource
    private UserService userService;

    //回显提示
    private String loginResult;

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toLoginUI(){
        return "loginUI";
    }


    //登录
    public String login(){

        if (user != null){
            if (StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword()))
            {
                //根据用户的账号和密码查询用户列表
                List<User> list = userService.findUserByAccountAndPassword(user.getAccount(),user.getPassword());

                if (list != null && list.size() > 0){

                    //保存信息到session
                    User user = list.get(0);
                    //把用户拥有的权限设置到session中
                    user.setUserRoles(userService.getUserRolesByUserId(user.getId()));

                    ServletActionContext.getRequest().getSession().setAttribute(Constant.USER,user);

                    //记录日志
                    log.info("用户名为:"+user.getName()+" 的用户登录了系统.");

                    return "home";

                }else {
                    loginResult = "账号或密码输入不正确!";
                }
            }
            else {
                loginResult = "请输入账号和密码!";
            }
        }
        return toLoginUI();
    }


    public String logout(){

        log.info("用户"+ ((User)ServletActionContext.getRequest().getSession().getAttribute(Constant.USER)).getName()+"退出登录.");

        ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);

        return toLoginUI();
    }

    public String toNoPermissionUI(){
        return "noPermissionUI";
    }

}
