package org.softaria.ams.app.api;

public interface WebPageService {

    WebPageDto getWebPageDto(String url);

    WebUrlsDto getWebUrlsDto();

    void addWebPage(WebPageDto request);

    void deleteWebPage(String url);
}
