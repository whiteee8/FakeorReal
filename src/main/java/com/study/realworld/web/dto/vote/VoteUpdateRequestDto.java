package com.study.realworld.web.dto.vote;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoteUpdateRequestDto {
    @ApiModelProperty(value = "투표된 게시글id")
    private Long articleId;

    @ApiModelProperty(value = "진품가품 여부")
    private int fakeTrue;

    @ApiModelProperty(value = "진품가품 여부")
    private int fakeFalse;

    @Builder
    public VoteUpdateRequestDto(Long articleId, int fakeTrue, int fakeFalse) {
        this.articleId = articleId;
        this.fakeTrue = fakeTrue;
        this.fakeFalse = fakeFalse;
    }
}
