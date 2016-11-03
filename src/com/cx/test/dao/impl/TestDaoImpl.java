package com.cx.test.dao.impl;

import com.cx.test.dao.TestDao;
import com.cx.test.entity.Person;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.io.Serializable;

/**
 * Created by cxspace on 16-8-23.
 */

//这里HibernateDaoSupport的版本必须时3的，检查Import语句
public class TestDaoImpl extends HibernateDaoSupport implements TestDao{


    @Override
    public Person findPerson(Serializable id) {
        return getHibernateTemplate().get(Person.class,id);
    }

    @Override
    public void save(Person person) {
        getHibernateTemplate().save(person);
    }
}
