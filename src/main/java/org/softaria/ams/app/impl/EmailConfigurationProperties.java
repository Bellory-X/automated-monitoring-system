package org.softaria.ams.app.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.mail")
public record EmailConfigurationProperties(
        String host,
        String username,
        String password,
        String protocol,
        String port,
        String address,
        String title
) {
}
