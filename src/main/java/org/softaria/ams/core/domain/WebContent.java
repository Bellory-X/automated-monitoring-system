package org.softaria.ams.core.domain;

import org.softaria.ams.platform.Validator;

public record WebContent(String value) {

    public WebContent {
        Validator.requireNonNull(value, "content");
    }
}
