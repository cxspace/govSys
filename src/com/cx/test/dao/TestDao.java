package com.cx.test.dao;

import com.cx.test.entity.Person;

import java.io.Serializable;

/**
 * Created by cxspace on 16-8-23.
 */
public interface TestDao {
    //保存人员
    public void save(Person person);

    //根据id查询人员
    public Person findPerson(Serializable id);
}
