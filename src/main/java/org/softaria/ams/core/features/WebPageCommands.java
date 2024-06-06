package org.softaria.ams.core.features;

import org.softaria.ams.app.dto.WebPageDto;
import org.softaria.ams.core.domain.WebContent;
import org.softaria.ams.core.domain.WebPage;
import org.softaria.ams.core.domain.WebUrl;
import org.softaria.ams.core.domain.data.WriteWebPageRepository;
import org.softaria.ams.platform.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebPageCommands {

    private final WriteWebPageRepository repository;

    @Autowired
    public WebPageCommands(WriteWebPageRepository repository) {
        this.repository = repository;
    }

    public void addWebPage(WebPageDto request) {
        Result.runCatching(() -> new WebPage(new WebUrl(request.getUrl()), new WebContent(request.getContent())))
                .onSuccess(repository::add)
                .orElseThrow();
    }

    public void deleteWebPage(String url) {
        Result.runCatching(() -> new WebUrl(url))
                .onSuccess(repository::removeByWebUrl)
                .orElseThrow();
    }

    public void archive() {
        repository.archive();
    }
}
