package com.study.realworld.web.dto.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleUpdateRequestDto {
    @ApiModelProperty(value = "slug")
    private String slug;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "설명")
    private String description;

    @ApiModelProperty(value = "내용")
    private String body;

    @Builder
    public ArticleUpdateRequestDto(String slug, String title, String description, String body) {
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.body = body;
    }
}
