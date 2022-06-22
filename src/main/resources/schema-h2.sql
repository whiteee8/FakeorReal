CREATE TABLE if not exists user
(
    id              bigint          NOT NULL AUTO_INCREMENT,
    email           varchar(50)     NOT NULL,
    username        varchar(20)     NOT NULL,
    password        varchar(255)    NOT NULL,
    bio             varchar         DEFAULT NULL,
    image           varchar         DEFAULT NULL,
    point           bigint          DEFAULT 0,
    rank            varchar(20)     DEFAULT NULL,
    favorite_cnt    bigint          DEFAULT 5,
    created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at      DATETIME        DEFAULT NULL,
    deleted_at      DATETIME        DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_email UNIQUE (email)
);

insert into user (id, email, username, password, point, rank) values (1, 'kim@naver.com', 'kim', 'kim456', 700, '전문가');
insert into user (id, email, username, password, point, rank) values (2, 'park@naver.com', 'park', 'park456', 600, '전문가');
insert into user (id, email, username, password, point, rank) values (3, 'min@naver.com', 'min', 'min123', 500, '전문가');
insert into user (id, email, username, password, point, rank) values (4, 'yang@naver.com', 'yang', 'yang456', 400, '전문가');
insert into user (id, email, username, password, point, rank) values (5, 'lee@naver.com', 'lee', 'lee456', 300, '전문가');

CREATE TABLE if not exists follow
(
    user_id         bigint          NOT NULL,
    follower_id     bigint          NOT NULL,
    PRIMARY KEY(user_id, follower_id),
    CONSTRAINT fk_follow_to_user_id FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    CONSTRAINT fk_follow_to_follower_id FOREIGN KEY (follower_id) REFERENCES user (id) ON DELETE CASCADE
);

CREATE TABLE if not exists article
(
    id              bigint          NOT NULL AUTO_INCREMENT,
    user_id         bigint          NOT NULL,
    kind            tinyint         NOT NULL DEFAULT 0,
    slug            varchar(50)     NOT NULL,
    title           varchar(50)     NOT NULL,
    description     varchar(255)    NOT NULL,
    body            text            NOT NULL,
    img             text            ,
    created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at      DATETIME        DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(),
    deleted_at      DATETIME        DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_article_to_user_id FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT unique_user_id_slug UNIQUE (user_id, slug)
);

insert into article (id, user_id, slug, title, description, body) values (1, 3, '구찌 지갑', '구찌 지갑', '의견 부탁', '저번 주 산 지갑인데 진짜일까요?');
insert into article (id, user_id, slug, title, description, body) values (2, 5, '샤넬 가방', '샤넬 가방', '의견 부탁', '친구가 선물해준 가방인데 진짜일까요?');
insert into article (id, user_id, slug, title, description, body) values (3, 4, '디올 구두', '디올 구두', '의견 부탁', '당근 마켓에서 샀는데 진짜일까요?');


CREATE TABLE if not exists comment
(
    id              bigint          NOT NULL AUTO_INCREMENT,
    user_id         bigint          NOT NULL,
    article_id     bigint          NOT NULL,
    body            text            NOT NULL,
    favorite        bigint          DEFAULT 0,
    created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at      DATETIME        DEFAULT NULL,
    deleted_at      DATETIME        DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_comment_to_user_id FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_comment_to_article_id FOREIGN KEY (article_id) REFERENCES article (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists favorite
(
    user_id         bigint          NOT NULL,
    article_id     bigint          NOT NULL,
    PRIMARY KEY (user_id, article_id),
    CONSTRAINT fk_favorite_to_user_id FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ,
    CONSTRAINT fk_favorite_to_article_id FOREIGN KEY (article_id) REFERENCES article (id) ON DELETE CASCADE
);

CREATE TABLE if not exists tag
(
    id              bigint          NOT NULL AUTO_INCREMENT,
    name            varchar(20)     NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE if not exists article_tag
(
    article_id      bigint          NOT NULL,
    tag_id          bigint          NOT NULL,
    PRIMARY KEY (article_id, tag_id),
    CONSTRAINT fk_articletag_article_id FOREIGN KEY (article_id) REFERENCES article (id) ON DELETE CASCADE,
    CONSTRAINT fk_articletag_tag_id FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE
);

CREATE TABLE if not exists ranking
(
    id              bigint          NOT NULL AUTO_INCREMENT,
    user_id         bigint          NOT NULL,
    rank            bigint          NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE if not exists vote
(
    id              bigint          NOT NULL AUTO_INCREMENT,
    articleId      bigint          NOT NULL,
    fakeTrue       int             DEFAULT 0,
    fakeFalse      int             DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT fk_vote_to_article_id FOREIGN KEY (articleId) REFERENCES article (id) ON DELETE CASCADE,
    CONSTRAINT unique_article_id UNIQUE (articleId)
);

CREATE TABLE if not exists vote_history
(
    id              bigint          NOT NULL AUTO_INCREMENT,
    articleId      bigint          NOT NULL,
    userId         bigint          NOT NULL,
    fake            tinyint         NOT NULL default true,
    PRIMARY KEY (id),
    CONSTRAINT fk_vote_history_to_user_id FOREIGN KEY (userId) REFERENCES user (id) ON DELETE CASCADE ,
    CONSTRAINT fk_vote_history_to_article_id FOREIGN KEY (articleId) REFERENCES article (id) ON DELETE CASCADE,
    CONSTRAINT unique_user_id_article_id UNIQUE (userId, articleId)
);

CREATE TABLE if not exists image
(
    id                  bigint      NOT NULL AUTO_INCREMENT,
    orderId             bigint      NOT NULL,
    originalFileName    varchar     NOT NULL,
    storedFilePath      varchar     NOT NULL ,
    fileSize            bigint      NOT NULL ,
    PRIMARY KEY (id)
);