package com.study.realworld.domain;

import java.util.Date;

public class Comment {

    private Long id;
    private Long user_id;
    private Long article_id;
    private String body;
    private Long favorite;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public Long getFavorite() {
        return favorite;
    }

    public void setFavorite(Long favorite) {
        this.favorite = favorite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}

//    id              bigint          NOT NULL AUTO_INCREMENT,
//        user_id         bigint          NOT NULL,
//        article_id     bigint          NOT NULL,
//        body            text            NOT NULL,
//        created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP(),
//        updated_at      DATETIME        DEFAULT NULL,
//        deleted_at      DATETIME        DEFAULT NULL,
//        PRIMARY KEY (id),
//        CONSTRAINT fk_comment_to_user_id FOREIGN KEY (user_id) REFERENCES user (id),
//        CONSTRAINT fk_comment_to_article_id FOREIGN KEY (article_id) REFERENCES article (id) ON DELETE CASCADE ON UPDATE CASCADE
