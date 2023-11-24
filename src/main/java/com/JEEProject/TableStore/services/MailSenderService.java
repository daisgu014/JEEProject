package com.JEEProject.TableStore.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;
    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hau.ln.fm090794@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    public void sendHTMLEmail(String toEmail,
                                String subject,
                                String body
    ) {
        MimeMessage message =  mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(toEmail);
            helper.setFrom("hau.ln.fm090794@gmail.com");
            helper.setSubject(subject);
            message.setContent(body,"text/html; charset=utf-8");
            new Thread(() -> mailSender.send(message)).start();
        } catch (MessagingException e) {
            System.out.println("mail send error");
            throw new RuntimeException(e);
        }


    }

}
