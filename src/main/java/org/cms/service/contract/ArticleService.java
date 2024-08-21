package org.cms.service.contract;

import org.cms.entity.Article;
import org.cms.service.dto.CreateArticleDto;
import org.cms.service.dto.UpdateArticle;

import java.util.ArrayList;

public interface ArticleService {
  void createArticle(CreateArticleDto dto);
  void updateArticle(UpdateArticle dto);
  ArrayList<Article> listArticles();
  Article getArticle(String articleId);
  void deleteArticle(String articleId);

}
