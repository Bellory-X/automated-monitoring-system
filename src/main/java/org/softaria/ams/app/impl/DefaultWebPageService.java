package org.softaria.ams.app.impl;

import org.softaria.ams.app.api.WebPageService;
import org.softaria.ams.app.impl.data.WebPageRepository;
import org.softaria.ams.app.api.WebUrlsDto;
import org.softaria.ams.app.api.WebPageDto;
import org.softaria.ams.util.LogBefore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultWebPageService implements WebPageService {

    private final WebPageRepository readRepository;

    @Autowired
    public DefaultWebPageService(WebPageRepository readRepository) {
        this.readRepository = readRepository;
    }

    @LogBefore
    public WebPageDto getWebPageDto(String url) {
        return readRepository.getWebPageByUrl(url);
    }

    @LogBefore
    public WebUrlsDto getWebUrlsDto() {
        return readRepository.getWebUrls();
    }

    @LogBefore
    public void addWebPage(WebPageDto request) {
        readRepository.addWebPage(request.getUrl(), request.getContent());
    }

    @LogBefore
    public void deleteWebPage(String url) {
        readRepository.removeWebPageByUrl(url);
    }
}
