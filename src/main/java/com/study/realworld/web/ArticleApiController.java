package com.study.realworld.web;

import com.study.realworld.service.article.ArticleService;
import com.study.realworld.web.dto.article.ArticleRequestDto;
import com.study.realworld.web.dto.article.ArticleSaveRequestDto;
import com.study.realworld.web.dto.article.ArticleUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags="게시물 관련 api")
public class ArticleApiController {
    private final ArticleService articleService;

    @PostMapping("/api/v1/article")
    @ApiOperation(value = "게시물 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestDto", value = "ArticleSaveRequestDto 참고", dataType = "ArticleSaveRequestDto", required = true)
    })
    public Long save(@RequestBody ArticleSaveRequestDto requestDto) throws Exception {
        return articleService.save(requestDto);
    }

    @PutMapping("/api/v1/article/{id}")
    @ApiOperation(value = "게시물 정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "게시물 고유 id", required = true, dataType = "Long", example = "0"),
            @ApiImplicitParam(name = "requestDto", value = "ArticleUpdateRequestDto 참고", dataType = "ArticleUpdateRequestDto", required = true)
    })
    public Long update(@PathVariable Long id, @RequestBody ArticleUpdateRequestDto requestDto) {
        return articleService.update(id, requestDto);
    }

    @GetMapping("/api/v1/article/{id}")
    @ApiOperation(value = "게시물 정보 조회")
    @ApiImplicitParam(name = "id", value = "게시물 고유 id", dataType = "Long", required = true, example = "0")
    public ArticleRequestDto findById(@PathVariable Long id) {
        return articleService.findById(id);
    }

    @GetMapping("/api/v1/article/list/{kind}")
    @ApiOperation(value = "게시물 리스트")
    @ApiImplicitParam(name = "kind", value = "게시판 종류", dataType = "int", required = true, example = "0")
    public List<ArticleRequestDto> findByKind(@PathVariable short kind) {
        return articleService.findByKind(kind);
    }
}
