package org.softaria.ams.core.domain.data;

import org.softaria.ams.core.domain.WebContent;
import org.softaria.ams.core.domain.WebPage;
import org.softaria.ams.core.domain.WebUrl;
import org.softaria.ams.core.exception.WebPageNotFoundException;
import org.softaria.ams.platform.Result;
import org.softaria.ams.platform.db.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WriteWebPageRepository {

    private final DataBase dataBase;

    @Autowired
    public WriteWebPageRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public Result<WebPage> findByWebUrl(WebUrl url) {
        return Result.runCatching(() -> dataBase.findContent(url.value()))
                .map(WebContent::new)
                .map(content -> new WebPage(url, content))
                .mapFailure(exception -> new WebPageNotFoundException(url.value()));
    }

    public void add(WebPage webPage) {
        dataBase.add(webPage.url().value(), webPage.content().value());
    }

    public void removeByWebUrl(WebUrl url) {
        dataBase.remove(url.value());
    }

    public void archive() {
        dataBase.archive();
    }
}
