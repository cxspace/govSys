package com.cx.nsfw.user.dao.impl;

import com.cx.core.dao.impl.BaseDaoImpl;
import com.cx.nsfw.user.entity.User;
import com.cx.nsfw.user.dao.UserDao;
import com.cx.nsfw.user.entity.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;


import java.io.Serializable;
import java.util.List;

/**
 * Created by cxspace on 16-8-23.
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public List<User> findUserByAccountAndId(String id, String account) {

        String hql = "FROM User WHERE account = ?";

        if (StringUtils.isNotBlank(id)){
            hql+=" AND id = ?";
        }

        Query query = getSession().createQuery(hql);
        query.setParameter(0,account);
        if (StringUtils.isNotBlank(id)){
          query.setParameter(1,id);
        }

        return query.list();
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        getHibernateTemplate().save(userRole);
    }

    @Override
    public void deleteUserRoleByUserId(Serializable id) {
        Query query = getSession().createQuery("DELETE FROM UserRole WHERE id.userId=?");
        query.setParameter(0,id);
        query.executeUpdate();
    }

    @Override
    public List<UserRole> getUserRolesByUserId(String id) {

        Query query = getSession().createQuery("FROM UserRole WHERE id.userId=?");
        query.setParameter(0,id);
        return query.list();
    }

    @Override
    public List<User> findUsersByAccountAndPass(String account, String password) {

        Query query = getSession().createQuery("FROM User WHERE account = ? and password =?");
        query.setParameter(0,account);
        query.setParameter(1,password);

        return query.list();
    }
}
