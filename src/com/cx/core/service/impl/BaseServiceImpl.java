package com.cx.core.service.impl;

import com.cx.core.dao.BaseDao;
import com.cx.core.service.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cx on 2016/11/03.
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    //怎么拿到baseDao
    private BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    
    @Override
    public void save(T entity) {
        baseDao.save(entity);
    }

    @Override
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }

    @Override
    public T findObjectById(Serializable id) {
        return baseDao.findObjectById(id);
    }

    @Override
    public List<T> findObjects() {
        return baseDao.findObjects();
    }
}
