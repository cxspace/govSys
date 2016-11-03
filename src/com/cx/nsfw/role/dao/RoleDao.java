package com.cx.nsfw.role.dao;

import com.cx.core.dao.BaseDao;
import com.cx.nsfw.role.entity.Role;

/**
 * Created by cx on 2016/10/21.
 */
public interface RoleDao extends BaseDao<Role>{
    //删除角色原来的所有权限
    public void deleteRolePrivilegeByRoleId(String roleId);

}
