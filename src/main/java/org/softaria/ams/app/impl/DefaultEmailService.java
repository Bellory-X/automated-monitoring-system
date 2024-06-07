package org.softaria.ams.app.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.softaria.ams.app.api.EmailService;
import org.softaria.ams.app.impl.data.WebPageRepository;
import org.softaria.ams.util.LogAfterThrowing;
import org.softaria.ams.util.LogBefore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmailService implements EmailService {

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

    @LogBefore
    @LogAfterThrowing
    public void sendEmail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(properties.username());
        helper.setTo(properties.address());
        helper.setSubject(properties.title());
        helper.setText(repository.getWebUrls().getMessage());
        mailSender.send(mimeMessage);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void sendEmailAndArchive() throws MessagingException {
        sendEmail();
        repository.archive();
    }
}
