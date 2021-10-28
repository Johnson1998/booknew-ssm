package com.john.utils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author John
 * @create 2021-10-2721:05
 */
public class SendMailUtil {
    //    授权码kvrisczyouxsbbfj
    public static String emailAccount = "644128256@qq.com";
    public static String emailPassword = "dfchgemuldrqbced";
    public static String emailSMTPHost = "smtp.qq.com";
    public static String receiveMailAccount = "";

    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String html) throws MessagingException, IOException {
        // 1.创建一封邮件对象
        MimeMessage message = new MimeMessage(session);
//        2.From:收件人
        message.setFrom(new InternetAddress(sendMail, "智慧书城管理员", "UTF-8"));
//        3. To:收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "同学", "UTF-8"));
//        4.Subject:邮件主题
        message.setSubject("智慧书城邮箱认证", "UTF-8");
//        5.Content邮件正文
        message.setContent(html, "text/html;charset=UTF-8");
//        6.设置发送时间
        message.setSentDate(new Date());
//        7.保存设置
        message.saveChanges();
        return message;
    }

}
