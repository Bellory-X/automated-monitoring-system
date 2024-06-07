package org.softaria.ams.app.api;

import com.google.common.collect.ImmutableList;
import org.softaria.ams.util.Validator;

public record WebUrlsDto(
        ImmutableList<String> deletedPageUrls,
        ImmutableList<String> updatedPageUrls,
        ImmutableList<String> createdPageUrls,
        ImmutableList<String> unmodifiedPageUrls
) {

    public WebUrlsDto {
        Validator.requireNonNull(deletedPageUrls, "deleted page urls");
        Validator.requireNonNull(updatedPageUrls, "updated page urls");
        Validator.requireNonNull(createdPageUrls, "created page urls");
        Validator.requireNonNull(unmodifiedPageUrls, "unmodified page urls");
    }

    public String getMessage() {
        return """
                Здравствуйте, дорогая и.о. секретаря
                                
                За последние сутки во вверенных Вам сайтах произошли следующие изменения:
                                
                Исчезли следующие страницы: {%s}
                Появились следующие новые страницы: {%s}
                Изменились следующие страницы: {%s}
                                
                С уважением,
                автоматизированная система
                мониторинга.
                """
                .formatted(
                        String.join(", ", deletedPageUrls),
                        String.join(", ", createdPageUrls),
                        String.join(", ", updatedPageUrls)
                );
    }
}
