package org.softaria.ams.app.api;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmail() throws MessagingException;
}
