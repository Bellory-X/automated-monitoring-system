package org.softaria.ams.app.queries.shared;

import com.google.common.collect.ImmutableList;
import org.softaria.ams.app.dto.WebUrlsDto;
import org.softaria.ams.core.exception.WebPageNotFoundException;
import org.softaria.ams.platform.Result;
import org.softaria.ams.platform.db.DataBase;
import org.springframework.stereotype.Repository;

@Repository
public class ReadWebPageRepository {

    private final DataBase dataBase;

    public ReadWebPageRepository(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public String getWebPageContentByUrl(String url) {
        return Result.runCatching(() -> dataBase.findContent(url))
                .mapFailure(exception -> new WebPageNotFoundException(url))
                .orElseThrow();
    }

    public WebUrlsDto getWebPageInfo() {
        return new WebUrlsDto(
                ImmutableList.<String>builder().addAll(dataBase.getDeletedPageUrls()).build(),
                ImmutableList.<String>builder().addAll(dataBase.getUpdatedPageUrls()).build(),
                ImmutableList.<String>builder().addAll(dataBase.getCreatedPageUrls()).build(),
                ImmutableList.<String>builder().addAll(dataBase.getUnmodifiedPageUrls()).build()
        );
    }
}
