package com.study.realworld.domain.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByKind(short kind);

    String findTitleById(Long article_id);
}
