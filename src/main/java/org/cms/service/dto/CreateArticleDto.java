package org.cms.service.dto;

public record CreateArticleDto(
        String title,
        String content,
        String authorId
) {
}
