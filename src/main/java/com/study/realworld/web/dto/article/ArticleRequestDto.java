package com.study.realworld.web.dto.article;

import com.study.realworld.domain.article.Article;
import com.study.realworld.domain.vote.Vote;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.Date;
import java.util.Optional;

@Getter
public class ArticleRequestDto {
    @ApiModelProperty(value = "게시글의 고유id")
    private Long id;

    @ApiModelProperty(value = "게시글을 쓴 유저의 고유id")
    private Long user_id;

    @ApiModelProperty(value = "slug")
    private String slug;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "설명")
    private String description;

    @ApiModelProperty(value = "내용")
    private String body;

    @ApiModelProperty(value = "게시글 작성 날짜")
    private Date created_at;

    @ApiModelProperty(value = "게시글 수정 날짜")
    private Date updated_at;

    @ApiModelProperty(value = "게시글 이미지 링크")
    private String imageLink;

    @ApiModelProperty(value = "가품이다")
    private int fakeTrue;

    @ApiModelProperty(value = "진품이다")
    private int fakeFalse;

    public ArticleRequestDto(Article entity, String imageLink, Optional<Vote> vote) {
        this.id = entity.getId();
        this.user_id = entity.getUser_id();
        this.slug = entity.getSlug();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.body = entity.getBody();
        this.created_at = entity.getCreated_at();
        this.updated_at = entity.getUpdated_at();
        this.imageLink = imageLink;
        vote.ifPresent(
                (v) -> {
                    this.fakeTrue = v.getFakeTrue();
                    this.fakeFalse = v.getFakeFalse();
                }
        );
    }
}
