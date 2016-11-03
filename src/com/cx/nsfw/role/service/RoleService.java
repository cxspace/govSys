package com.cx.nsfw.role.service;

import com.cx.core.exception.ServiceException;
import com.cx.nsfw.role.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cx on 2016/10/21.
 */
public interface RoleService {

    public void save(Role role);

    public void update(Role role);

    public void delete(Serializable id);

    public Role findObjectById(Serializable id);

    public List<Role> findObiects() throws ServiceException;

}
