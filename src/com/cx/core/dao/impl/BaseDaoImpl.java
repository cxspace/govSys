package com.cx.core.dao.impl;

//这里必须选3的版本
import com.cx.core.dao.BaseDao;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by cxspace on 16-8-23.
 */
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{

    Class<T> clazz;

    public BaseDaoImpl(){
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // BaseDaoImpl<User>
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void delete(Serializable id) {
        getHibernateTemplate().delete(findObjectById(id));
    }

    @Override
    public void save(Object entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public void update(Object entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public T findObjectById(Serializable id) {
        return getHibernateTemplate().get(clazz,id);
    }

    @Override
    public List<T> findObjects() {
        Query query = getSession().createQuery("FROM "+ clazz.getSimpleName());
        return query.list();
    }
}
