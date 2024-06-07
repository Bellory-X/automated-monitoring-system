package org.softaria.ams.app.impl.data;

import com.google.common.collect.ImmutableList;
import org.softaria.ams.app.api.WebPageDto;
import org.softaria.ams.app.api.WebUrlsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WebPageRepository {

    private final DataBase dataBase;

    @Autowired
    public WebPageRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public WebPageDto getWebPageByUrl(String url) {
        if (url == null || url.isEmpty()) {
            return new WebPageDto(url, "");
        }
        return new WebPageDto(url, dataBase.findContent(url));
    }

    public WebUrlsDto getWebUrls() {
        return new WebUrlsDto(
                ImmutableList.<String>builder().addAll(dataBase.getDeletedPageUrls()).build(),
                ImmutableList.<String>builder().addAll(dataBase.getUpdatedPageUrls()).build(),
                ImmutableList.<String>builder().addAll(dataBase.getCreatedPageUrls()).build(),
                ImmutableList.<String>builder().addAll(dataBase.getUnmodifiedPageUrls()).build()
        );
    }

    public void addWebPage(String url, String content) {
        dataBase.add(url, content);
    }

    public void removeWebPageByUrl(String url) {
        dataBase.remove(url);
    }

    public void archive() {
        dataBase.archive();
    }
}
