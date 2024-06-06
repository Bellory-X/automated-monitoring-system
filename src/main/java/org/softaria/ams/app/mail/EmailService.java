package org.softaria.ams.app.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softaria.ams.app.queries.features.WebPageQueries;
import org.softaria.ams.core.features.WebPageCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final EmailConfigurationProperties properties;
    private final JavaMailSender mailSender;
    private final WebPageQueries queries;
    private final WebPageCommands commands;

    @Autowired
    public EmailService(
            EmailConfigurationProperties properties,
            JavaMailSender mailSender,
            WebPageQueries queries,
            WebPageCommands commands
    ) {
        this.properties = properties;
        this.mailSender = mailSender;
        this.queries = queries;
        this.commands = commands;
    }

    public void sendEmail() {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom(properties.username());
            helper.setTo(properties.address());
            helper.setSubject(properties.title());
            helper.setText(queries.getWebPageInfo().getMessage());
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void sendEmailAndArchive() {
        sendEmail();
        commands.archive();
    }
}
