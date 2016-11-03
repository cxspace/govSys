package com.cx.nsfw.role.service.impl;

import com.cx.core.exception.ServiceException;
import com.cx.core.service.impl.BaseServiceImpl;
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
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{


    private RoleDao roleDao;

    @Resource
    public void setRoleDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
    }

    @Override
    public void update(Role role) {

        //1.删除该角色对应的所有权限
        roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
        //2.更新角色的所有权限
        roleDao.update(role);
    }

}
