package com.study.realworld.web.dto.vote;

import com.study.realworld.domain.vote.Vote;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class VoteArticleRequestDto {
    @ApiModelProperty(value = "게시글 고유id")
    private Long id;

    @ApiModelProperty(value = "게시글")
    private String title;

    @ApiModelProperty(value = "가품")
    private int fakeTrue;

    @ApiModelProperty(value = "진품")
    private int fakeFalse;

    public VoteArticleRequestDto(String name, Vote entity) {
        this.id = entity.getId();
        this.title = name;
        this.fakeTrue = entity.getFakeTrue();
        this.fakeFalse = entity.getFakeFalse();
    }
}
