package com.cx.core.service;

import com.cx.core.util.QueryHelper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cx on 2016/11/03.
 */
public interface BaseService <T>{

    public void save(T  entity);

    public void update(T entity);

    public void delete(Serializable id);

    public T findObjectById(Serializable id);

    public List<T> findObjects();

    //条件查询实体列表

    public List<T> findObjects(String hql , List<Object> parameters);

    public List<T> findObjects(QueryHelper queryHelper);

}
