package com.cx.nsfw.role.entity;

import java.io.Serializable;

/**
 * Created by cx on 2016/10/21.
 *
 * 联合主键类
 *
 */
public class RolePrivilegeId implements Serializable{

    private Role role;

    private String code;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RolePrivilegeId(Role role) {
        this.role = role;
    }

    public RolePrivilegeId(Role role, String code) {
        this.role = role;
        this.code = code;
    }

    public RolePrivilegeId() {
    }
}
