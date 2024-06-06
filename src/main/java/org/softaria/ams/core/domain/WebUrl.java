package org.softaria.ams.core.domain;

import org.softaria.ams.platform.Validator;

public record WebUrl(String value) {

    public WebUrl {
        Validator.requireNonNull(value, "web url");
    }
}
