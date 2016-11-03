package com.cx.test.service.impl;

import com.cx.test.dao.TestDao;
import com.cx.test.entity.Person;
import com.cx.test.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by cxspace on 16-8-23.
 */


@Service("testService")
public class TestServiceImpl implements TestService{

    @Resource
    TestDao testDao;

    @Override
    public void say() {
        System.out.println("hi");
    }

    @Override
    public Person findPerson(Serializable id) {
       // save(new Person("test")); // Write operations are not allowed in read-only mode
        return testDao.findPerson(id);
    }

    @Override
    public void save(Person person) {
        testDao.save(person);

        //测试事务回滚
        //int i = 1  / 0; //java.lang.ArithmeticException: / by zero
    }
}
