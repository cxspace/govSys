package com.cx.nsfw.user.entity;

import com.cx.nsfw.role.entity.Role;

import java.io.Serializable;

/**
 * Created by cx on 2016/10/22.
 */
public class UserRoleId implements Serializable{

    private Role role;

    private String userId;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserRoleId(Role role, String userId) {
        this.role = role;
        this.userId = userId;
    }

    public UserRoleId() {
    }
}
