package com.cx.nsfw.role.entity;

/**
 * Created by cx on 2016/10/21.
 *
 * 角色权限
 *
 */
public class RolePrivilege {

    private RolePrivilegeId id;

    public RolePrivilegeId getId() {
        return id;
    }

    public void setId(RolePrivilegeId id) {
        this.id = id;
    }

    public RolePrivilege(RolePrivilegeId id) {
        this.id = id;
    }

    public RolePrivilege() {
    }
}
