package org.softaria.ams.core.exception;

public class WebPageNotFoundException extends RuntimeException {

    String url;

    public WebPageNotFoundException(String url) {
        this.url = url;
    }

    public String getMessage() {
        return "Web page not found by url: " + url;
    }
}
