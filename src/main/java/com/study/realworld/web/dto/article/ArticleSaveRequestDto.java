package com.study.realworld.web.dto.article;

import com.study.realworld.domain.article.Article;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleSaveRequestDto {
    @ApiModelProperty(value = "게시물을 쓴 유저의 id")
    private Long userId;

    @ApiModelProperty(value = "게시판 종류")
    private short kind;

    @ApiModelProperty(value = "slug")
    private String slug;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "설명")
    private String description;

    @ApiModelProperty(value = "내용")
    private String body;

    @ApiModelProperty(value = "사진")
    private String img;

    @Builder
    public ArticleSaveRequestDto(Long userId, short kind, String slug, String title, String description, String body, String img) {
        this.userId = userId;
        this.kind = kind;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
        this.img = img;
    }

    public Article toEntity() {
        return Article.builder()
                .user_id(userId)
                .kind(kind)
                .slug(slug)
                .title(title)
                .description(description)
                .body(body)
                .img(img)
                .build();
    }
}
