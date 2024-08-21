package org.cms.service.impl;

import org.cms.entity.Article;
import org.cms.service.contract.ArticleService;
import org.cms.service.dto.CreateArticleDto;
import org.cms.service.dto.UpdateArticle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ArticleServiceImpl implements ArticleService {
    private  final HashMap<String, Article> articles = new HashMap<>();

    private ArrayList<Article> getArticlesList() {
        return new ArrayList<>(articles.values());
    }


    public void createArticle(CreateArticleDto dto) {
        var article = new Article(dto.title(), dto.content(), dto.authorId());
        articles.put(article.getId(), article);
    }


    public void updateArticle(UpdateArticle updateArticle) {
        var article = articles.get(updateArticle.articleId());

        if (article == null) {
            throw new RuntimeException("Artigo não encontrado");
        }

        updateArticle.title().ifPresent(article::setTitle);
        updateArticle.content().ifPresent(article::setContent);

        articles.put(article.getId(), article);
    }


    public ArrayList<Article> listArticlesByAuthor(String authorId) {
       return getArticlesList()
               .stream()
               .filter(article -> article.getAuthorId().equals(authorId))
               .collect(Collectors.toCollection(ArrayList::new));

    }

    public ArrayList<Article> listArticles() {
        return getArticlesList();
    }

    public Article getArticle(String articleId) {
        return articles.get(articleId);
    }

    public void deleteArticle(String articleId) {
        articles.remove(articleId);
    }

}
