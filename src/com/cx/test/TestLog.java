package com.cx.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Created by cxspace on 16-8-23.
 */
public class TestLog {

    @Test
    public void test(){

        Log log =  LogFactory.getLog(getClass());

        try {
            int i = 1 / 0;
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

        log.debug("debug日志级别");
        log.info("info日志级别");
        log.warn("warn日志级别");
        log.error("error日志级别");
        log.fatal("fatal日志级别");
    }
}
