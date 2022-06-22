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
public class VoteHistorySaveRequestDto {
    @ApiModelProperty(value = "투표된 게시글id")
    private Long articleId;

    @ApiModelProperty(value = "투표를 쓴 유저의 고유id")
    private Long userId;

    @ApiModelProperty(value = "진품가품 여부")
    private boolean fake;

    @Builder
    public VoteHistorySaveRequestDto(Long articleId, Long userId, boolean fake) {
        this.articleId = articleId;
        this.userId = userId;
        this.fake = fake;
    }

    public VoteHistory toEntity() {
        return VoteHistory.builder()
                .articleId(articleId)
                .userId(userId)
                .fake(fake)
                .build();
    }
}
