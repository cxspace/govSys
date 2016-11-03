package com.cx.test;

import com.cx.test.entity.Person;
import com.cx.test.service.TestService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by cxspace on 16-8-22.
 */
public class TestMerge {

    private ClassPathXmlApplicationContext context;

    @Before
    public void loadCtx(){

        context = new ClassPathXmlApplicationContext("bean-base.xml");

        TestService ts = (TestService) context.getBean("testService");

        ts.say();

    }

    @Test
   //测试hibernate
    public void testHibernate(){

        SessionFactory sf = (SessionFactory)context.getBean("sessionFactory");

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        session.save(new Person("人员1"));

        tx.commit();

        session.close();

    }



    //测试service和dao层
    public void testServiceAndDao(){

        TestService testService
                 = (TestService)context.getBean("testService");
//        testService.save(new Person("人员2"));
        System.out.println(testService.findPerson("ff80818156b57b720156b57b748a0000").getName());
    }



    public void testTransationReadOnly(){
        TestService testService
                = (TestService)context.getBean("testService");
        System.out.println(testService.findPerson("ff80818156b57b720156b57b748a0000").getName());
    }


    public void testTxRollback(){
        TestService ts = (TestService)context.getBean("testService");
        ts.save(new Person("人员3"));
    }

}
