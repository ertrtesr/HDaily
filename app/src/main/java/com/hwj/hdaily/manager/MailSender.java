package com.hwj.hdaily.manager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hwj.hdaily.auth.MyAuthenticator;
import com.hwj.hdaily.model.entity.MailSenderInfo;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件给多个接收者、抄送邮件
 */
public class MailSender {

    /**
     * 以文本格式发送邮件
     *
     * @param mailInfo 待发送的邮件的信息
     */
    public boolean sendTextMail(MailSenderInfo mailInfo) {
        Session sendMailSession = createSession(mailInfo);
        try {
            Message mailMessage = createMailMessage(mailInfo, sendMailSession);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 创建一个发送邮件的session
     *
     * @param mailInfo
     * @return
     */
    private Session createSession(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        return Session.getDefaultInstance(pro, authenticator);
    }

    /**
     * 创建邮件封装成一个Message
     *
     * @param mailInfo
     * @param sendMailSession
     * @return
     * @throws MessagingException
     */
    @NonNull
    private Message createMailMessage(MailSenderInfo mailInfo, Session sendMailSession) throws MessagingException {
        // 根据session创建一个邮件消息
        Message mailMessage = new MimeMessage(sendMailSession);
        // 创建邮件发送者地址
        Address from = new InternetAddress(mailInfo.getFromAddress());
        // 设置邮件消息的发送者
        mailMessage.setFrom(from);
        Address[] tos = createTos(mailInfo);
        Address[] ccs = createCcs(mailInfo);
        // Message.RecipientType.TO属性表示接收者的类型为TO
        mailMessage.setRecipients(Message.RecipientType.TO, tos);
        // 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
        mailMessage.setRecipients(Message.RecipientType.CC, ccs);
        // 设置邮件消息的主题
        mailMessage.setSubject(mailInfo.getSubject());
        // 设置邮件消息发送的时间
        mailMessage.setSentDate(new Date());
        // 设置邮件消息的主要内容
        String mailContent = mailInfo.getContent();
        mailMessage.setText(mailContent);
        return mailMessage;
    }

    /**
     * 创建发送者
     *
     * @param mailInfo
     * @return
     * @throws AddressException
     */
    @Nullable
    private Address[] createTos(MailSenderInfo mailInfo) throws AddressException {
        // 创建邮件的接收者地址，并设置到邮件消息中
        Address[] toAddresses = null;
        String[] receivers = mailInfo.getReceivers();       //里面存的都是String类型的邮件地址
        if (receivers != null && receivers.length > 0) {
            toAddresses = new InternetAddress[receivers.length];
            // 为每个邮件接收者创建一个地址
            for (int i = 0; i < receivers.length; i++) {
                toAddresses[i] = new InternetAddress(receivers[i]);
            }
        }
        return toAddresses;
    }

    /**
     * 创建抄送者
     *
     * @param mailInfo
     * @return
     * @throws MessagingException
     */
    private Address[] createCcs(MailSenderInfo mailInfo) throws AddressException {
        // 获取抄送者信息
        Address[] ccAddresses = null;
        String[] ccs = mailInfo.getCcs();
        if (ccs != null && ccs.length > 0) {
            // 为每个邮件接收者创建一个地址
            ccAddresses = new InternetAddress[ccs.length];
            for (int i = 0; i < ccs.length; i++) {
                ccAddresses[i] = new InternetAddress(ccs[i]);
            }
        }
        return ccAddresses;
    }
}