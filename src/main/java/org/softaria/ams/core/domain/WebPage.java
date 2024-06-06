package org.softaria.ams.core.domain;

import org.softaria.ams.platform.Validator;

public record WebPage(WebUrl url, WebContent content) {

    public WebPage {
        Validator.requireNonNull(url, "url");
        Validator.requireNonNull(content, "content");
    }
}
