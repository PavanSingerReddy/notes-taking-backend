package com.pavansingerreddy.note.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// @Service is a Spring annotation that marks this class as a service in the Spring application context.
// This means that Spring will automatically create an instance of this class and manage it.
@Service
// EmailService is a service class which is used for sending Emails
public class EmailService {

    // @Autowired is a Spring annotation for automatic dependency injection.
    // Here, it's injecting an instance of JavaMailSender, which is a Spring
    // interface for sending emails.
    @Autowired
    @Qualifier("mailSender1")
    private JavaMailSender mailSender1;

    @Autowired
    @Qualifier("mailSender2")
    private JavaMailSender mailSender2;

    @Value("${mail.config1.username}")
    private String fromEmail1;

    @Value("${mail.config2.username}")
    private String fromEmail2;


    // This method is used to send an email. It takes the recipient's email address,
    // email subject, and email body as parameters.
    public boolean sendEmail(String toEmail, String subject, String body) {

        // Creating a new instance of SimpleMailMessage which is a helper class for
        // creating a JavaMail MimeMessage.
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // Setting the recipient's email address.
        mailMessage.setTo(toEmail);
        // Setting the body of the email.
        mailMessage.setText(body);
        // Setting the subject of the email.
        mailMessage.setSubject(subject);

        // Sending the email using the JavaMailSender instance.
        // If the email is sent successfully, the method returns true.
        // If there's an exception (email not sent), the method returns false.
        try {
            mailMessage.setFrom(fromEmail1);
            mailSender1.send(mailMessage);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to send email to " + toEmail + " using mail configuration 1");
            System.out.println(e);
        }

        try {
            mailMessage.setFrom(fromEmail2);
            mailSender2.send(mailMessage);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to send email to " + toEmail + " using mail configuration 2");
            System.out.println(e);
        }

        return false;

    }

}
