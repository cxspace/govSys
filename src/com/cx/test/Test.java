package com.cx.test;

import java.io.IOException;

/**
 * Created by cx on 2016/10/22.
 */
public class Test {
    public static void main(String[] args){

            MailMessage message = new MailMessage();
            message.setFrom("13037239781@163.com");
            message.setTo("442961832@qq.com");
            message.setSubject("神通录注册验证");
            message.setUser("13037239781@163.com");
            message.setContent("点击链接验证 http://www.huangjiacheng.top:8080/shenTongFront/login.html");
            message.setDatafrom("13037239781@163.com");
            message.setDatato("442961832@qq.com");
            message.setPassword("33269456cx");

            SendMail send = SendMailImp.getInstance(SendMailImp.WANGYI163).setMessage(message);
            try {
                send.sendMail();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}
