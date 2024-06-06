package org.softaria.ams.app.queries.features;

import org.softaria.ams.app.queries.shared.ReadWebPageRepository;
import org.softaria.ams.app.dto.WebUrlsDto;
import org.softaria.ams.app.dto.WebPageDto;
import org.springframework.stereotype.Service;

@Service
public class WebPageQueries {

    private final ReadWebPageRepository repository;

    public WebPageQueries(ReadWebPageRepository repository) {
        this.repository = repository;
    }

    public WebPageDto getWebPageContent(String url) {
        return new WebPageDto(url, repository.getWebPageContentByUrl(url));
    }

    public WebUrlsDto getWebPageInfo() {
        return repository.getWebPageInfo();
    }
}
