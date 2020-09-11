package com.example.miniorange_demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service("mailService")
public class DefaultEmailService implements EmailService {

    @Autowired
    public JavaMailSender emailsender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    public DefaultEmailService(JavaMailSender javaMailSender){
        this.emailsender =javaMailSender;
    }
    @Override
    public void sendSimpleEmail(User mail,String description,String email_input) {


        MimeMessage mimeMessage = emailsender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariable("description",description);
            context.setVariable("email_input",email_input);
            String html = templateEngine.process("email-template",context);

            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(html,true);

            emailsender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
