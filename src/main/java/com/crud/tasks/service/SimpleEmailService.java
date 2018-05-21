package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SimpleEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    public void send(final Mail mail) {

        LOGGER.info("Staring email preparation...");

        try {
            SimpleMailMessage mailMassage = createMailMassage(mail);
            javaMailSender.send(mailMassage);

            LOGGER.info("Email has been sent");
        } catch (MailException e) {
            LOGGER.error("Failed to send an email: ", e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMassage(final Mail mail) {
        SimpleMailMessage mailMassage = new SimpleMailMessage();
        mailMassage.setTo(mail.getMailTo());
        mailMassage.setSubject(mail.getSubject());
        mailMassage.setText(mail.getMessage());
        return mailMassage;
    }
}
