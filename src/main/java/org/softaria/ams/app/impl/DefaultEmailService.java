package org.softaria.ams.app.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softaria.ams.app.api.EmailService;
import org.softaria.ams.app.impl.data.WebPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmailService implements EmailService {

    private final Logger logger = LoggerFactory.getLogger(DefaultEmailService.class);
    private final EmailConfigurationProperties properties;
    private final JavaMailSender mailSender;
    private final WebPageRepository repository;

    @Autowired
    public DefaultEmailService(
            EmailConfigurationProperties properties,
            JavaMailSender mailSender,
            WebPageRepository repository
    ) {
        this.properties = properties;
        this.mailSender = mailSender;
        this.repository = repository;
    }

    public void sendEmail() {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom(properties.username());
            helper.setTo(properties.address());
            helper.setSubject(properties.title());
            helper.setText(repository.getWebUrls().getMessage());
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void sendEmailAndArchive() {
        sendEmail();
        repository.archive();
    }
}
