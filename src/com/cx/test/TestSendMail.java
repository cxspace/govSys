package com.cx.test;

/**
 * Created by cx on 2016/10/22.
 */

import org.junit.Test;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;



public class TestSendMail {

    @Test
    public void testSend() throws Exception {

//
//        //0. 邮件参数
//        Properties prop = new Properties();
//        prop.put("mail.transport.protocol", "smtp");	// 指定协议
//        prop.put("mail.smtp.host", "stmp.qq.com");		// 主机   stmp.qq.com
////        prop.put("mail.smtp.port", 25);					// 端口
//        prop.put("mail.smtp.auth", "true");				// 用户密码认证
////        prop.put("mail.debug", "true");					// 调试模式
//
//        //1. 创建一个邮件的会话
//        Session session = Session.getInstance(prop);
//
//        session.setDebug(true);
//
//        Transport ts = session.getTransport();
//
//        ts.connect("smtp","4429618322qq.com","izmljpslupiacage");
//
//        Message message = createSimpleMail(session);
//
//        ts.sendMessage(message,message.getAllRecipients());
//
//        ts.close();
//
//
//    }
//
//    public  MimeMessage createSimpleMail(Session session) throws Exception{
//
//        MimeMessage message = new MimeMessage(session);
//
//        message.setFrom(new InternetAddress("442961832@qq.com"));
//
//        message.setRecipient(Message.RecipientType.TO,new InternetAddress("442961832qq.com"));
//
//        message.setSubject("简单文本");
//
//        message.setContent("aaa","text/html;charset=UTF-8");
//
//        return message;
 }

}
