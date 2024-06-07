package org.softaria.ams.app.impl;

import org.softaria.ams.app.api.WebPageService;
import org.softaria.ams.app.impl.data.WebPageRepository;
import org.softaria.ams.app.api.WebUrlsDto;
import org.softaria.ams.app.api.WebPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultWebPageService implements WebPageService {

    private final WebPageRepository readRepository;

    @Autowired
    public DefaultWebPageService(WebPageRepository readRepository) {
        this.readRepository = readRepository;
    }

    public WebPageDto getWebPageDto(String url) {
        return readRepository.getWebPageByUrl(url);
    }

    public WebUrlsDto getWebUrlsDto() {
        return readRepository.getWebUrls();
    }

    public void addWebPage(WebPageDto request) {
        readRepository.addWebPage(request.getUrl(), request.getContent());
    }

    public void deleteWebPage(String url) {
        readRepository.removeWebPageByUrl(url);
    }
}
