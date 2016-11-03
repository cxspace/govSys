package com.cx.nsfw.role.service.impl;

import com.cx.core.exception.ServiceException;
import com.cx.nsfw.role.dao.RoleDao;
import com.cx.nsfw.role.entity.Role;
import com.cx.nsfw.role.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by cx on 2016/10/21.
 */


@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleDao roleDao;

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void update(Role role) {

        //1.删除该角色对应的所有权限
        roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
        //2.更新角色的所有权限
        roleDao.update(role);
    }

    @Override
    public void delete(Serializable id) {
        roleDao.delete(id);
    }

    @Override
    public Role findObjectById(Serializable id) {
        return roleDao.findObjectById(id);
    }

    @Override
    public List<Role> findObiects() throws ServiceException {
        return roleDao.findObjects();
    }
}
