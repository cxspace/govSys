package com.cx.nsfw.role.entity;

import java.util.Set;

/**
 * Created by cx on 2016/10/21.
 *
 * 角色实体
 *
 */
public class Role {

    private String roleId;

    private String name;

    private String status;


    //角色状态
    private static String ROLE_STATE_VALID="1";  //角色有效

    private static String ROLE_STATE_INVALID="0";  //角色无效


    private Set<RolePrivilege> rolePrivileges;

    public Role(String roleId, String name, String status, Set<RolePrivilege> rolePrivileges) {
        this.roleId = roleId;
        this.name = name;
        this.status = status;
        this.rolePrivileges = rolePrivileges;
    }

    public Role() {
    }

    public Role(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<RolePrivilege> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }
}
