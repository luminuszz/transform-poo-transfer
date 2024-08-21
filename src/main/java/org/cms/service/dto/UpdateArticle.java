package org.cms.service.dto;

import java.util.Optional;

public record UpdateArticle(
        String articleId,
        Optional<String> title,
        Optional<String> content
) {


    public UpdateArticle(String articleId, String title, String content) {
        this(articleId, Optional.ofNullable(title), Optional.ofNullable(content));
    }
}
