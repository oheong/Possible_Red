package com.ssafy.SNS201.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailServiceImpl implements MailService {

    private Log log = LogFactory.getLog(MailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean send(String subject, String text, String to) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject); // 제목
            helper.setText(text, true); // 내용
            helper.setTo(to); // 보낼 대상

            javaMailSender.send(message);
            return true;
        }
        catch (MessagingException e){
            e.printStackTrace();
        }
        return false;
    }

}
