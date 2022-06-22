package com.study.realworld.web;

import com.study.realworld.service.vote.VoteService;
import com.study.realworld.web.dto.vote.VoteArticleRequestDto;
import com.study.realworld.web.dto.vote.VoteHistorySaveRequestDto;
import com.study.realworld.web.dto.vote.VoteRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags="투표 관련 api")
public class VoteApiController {
    private final VoteService voteService;

    @PostMapping("/api/v1/vote")
    @ApiOperation(value = "투표 등록")
    @ApiImplicitParam(name = "requestDto", value = "VoteHistorySaveRequestDto 참고", dataType = "VoteHistorySaveRequestDto", required = true)
    public Long save(@RequestBody VoteHistorySaveRequestDto requestDto) {
        return voteService.save(requestDto);
    }

    @PutMapping("/api/v1/vote/{id}")
    @ApiOperation(value = "투표 정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "투표 고유 id", required = true, dataType = "Long", example = "0"),
            @ApiImplicitParam(name = "id", value = "유저 고유 id", required = true, dataType = "Long", example = "0"),
            @ApiImplicitParam(name = "requestDto", value = "VoteUpdateRequestDto 참고", dataType = "VoteUpdateRequestDto", required = true)
    })
    public Long update(@PathVariable Long id, @RequestBody Long user_id, @RequestBody Boolean fake) {
        return voteService.update(id, user_id, fake);
    }

    @GetMapping("/api/v1/vote/list")
    @ApiOperation(value = "투표 리스트")
    public List<VoteArticleRequestDto> findTop10By() {
        return voteService.findTop10By();
    }
}
