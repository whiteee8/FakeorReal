package com.study.realworld.web.dto.vote;

import com.study.realworld.domain.vote.Vote;
import com.study.realworld.domain.vote.VoteHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoteSaveRequestDto {
    @ApiModelProperty(value = "투표된 게시글id")
    private Long articleId;

    @ApiModelProperty(value = "진품가품 여부")
    private int fakeTrue;

    @ApiModelProperty(value = "진품가품 여부")
    private int fakeFalse;

    @Builder
    public VoteSaveRequestDto(VoteHistorySaveRequestDto requestDto) {
        this.articleId = requestDto.getArticleId();
        this.fakeTrue = requestDto.isFake()?this.fakeTrue++:this.fakeTrue;
        this.fakeFalse = requestDto.isFake()?this.fakeFalse:this.fakeFalse++;
    }

    public Vote toEntity() {
        return Vote.builder()
                .articleId(articleId)
                .fakeTrue(fakeTrue)
                .fakeFalse(fakeFalse)
                .build();
    }
}
