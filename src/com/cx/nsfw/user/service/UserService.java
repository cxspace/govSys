package com.cx.nsfw.user.service;

import com.cx.core.exception.ServiceException;
import com.cx.core.service.BaseService;
import com.cx.nsfw.user.entity.User;
import com.cx.nsfw.user.entity.UserRole;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by cxspace on 16-8-23.
 */
public interface UserService extends BaseService<User> {


    //导出用户列表
    public void importExcel(File userExcel, String fileType);

    public void exportExcel(List<User> userList, ServletOutputStream outputStream);

    //ajax异步验证用户是否存在
    public List<User> findUserByAccountAndId(String id, String account);

    //保存用户及其所对应的角色
    //可变参数
    public void saveUserAndRole(User user, String... roleIds);

    //更新用户及其所对应的角色
    public void updateUserAndRole(User user, String... roleIds);

    //更具用户id查询用户角色
    public List<UserRole> getUserRolesByUserId(String id);

    public List<User> findUserByAccountAndPassword(String account, String password);
}




