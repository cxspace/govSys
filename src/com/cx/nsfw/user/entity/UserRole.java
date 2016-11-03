package com.cx.nsfw.user.entity;

import java.io.Serializable;

/**
 * Created by cx on 2016/10/22.
 *
 * 用户角色类
 *
 */
public class UserRole implements Serializable{

    private UserRoleId id;

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    public UserRole(UserRoleId id) {
        this.id = id;
    }

    public UserRole() {
    }
}
