package com.cx.nsfw.user.dao;

import com.cx.core.dao.BaseDao;
import com.cx.nsfw.user.entity.User;
import com.cx.nsfw.user.entity.UserRole;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cxspace on 16-8-23.
 */
public interface UserDao extends BaseDao<User>{

    //根据账号和用户id查询用户
    public List<User> findUserByAccountAndId(String id, String account);

    //保存用户角色
    public void saveUserRole(UserRole userRole);

    //根据用户id删除该用户的所有角色
    public void deleteUserRoleByUserId(Serializable id);

    //根据用户id获取改用户对应的所有用户角色
    public List<UserRole> getUserRolesByUserId(String id);

    //更具用户的账号和密码查询用户列表
    public List<User> findUsersByAccountAndPass(String account, String password);

}
