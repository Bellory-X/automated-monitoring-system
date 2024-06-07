package org.softaria.ams.app.api;

import java.util.Objects;

public final class WebPageDto {
    private String url;
    private String content;

    public WebPageDto(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WebPageDto) obj;
        return Objects.equals(this.url, that.url) &&
                Objects.equals(this.content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, content);
    }

    @Override
    public String toString() {
        return "AddWebPageRequest[" +
                "url=" + url + ", " +
                "content=" + content + ']';
    }

}
