package com.cx.nsfw.role.dao.impl;

import com.cx.core.dao.impl.BaseDaoImpl;
import com.cx.nsfw.role.dao.RoleDao;
import com.cx.nsfw.role.entity.Role;
import org.hibernate.Query;

/**
 * Created by cx on 2016/10/21.
 */
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
    @Override
    public void deleteRolePrivilegeByRoleId(String roleId) {

        //删除角色所具有的所有权限

        Query query = getSession().createQuery("DELETE FROM RolePrivilege WHERE id.role.roleId=?");

        query.setParameter(0,roleId);

        query.executeUpdate();

    }
}
