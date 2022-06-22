package com.study.realworld.service;

import com.study.realworld.domain.article.Article;
import com.study.realworld.repository.ArticleRepositorySub;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceSub {

    private final ArticleRepositorySub articleRepository;

    public ArticleServiceSub(ArticleRepositorySub articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article register(Article article){
        return articleRepository.save(article);
    }

    public Article findBySlug(String slug){
        Article findArticle = articleRepository.findBySlug(slug);
        return findArticle;
    }
}
