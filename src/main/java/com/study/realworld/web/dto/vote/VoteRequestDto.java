package com.study.realworld.web.dto.vote;

import com.study.realworld.domain.vote.Vote;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class VoteRequestDto {
    @ApiModelProperty(value = "투표 고유id")
    private Long id;

    @ApiModelProperty(value = "투표된 게시글id")
    private Long articleId;

    @ApiModelProperty(value = "진품가품 여부")
    private int fakeTrue;

    @ApiModelProperty(value = "진품가품 여부")
    private int fakeFalse;

    public VoteRequestDto(Vote entity) {
        this.id = entity.getId();
        this.articleId = entity.getArticleId();
        this.fakeTrue = entity.getFakeTrue();
        this.fakeFalse = entity.getFakeFalse();
    }
}
