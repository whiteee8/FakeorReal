package com.study.realworld.web.dto.article;

import com.study.realworld.domain.article.Article;
import com.study.realworld.domain.article.Image;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;


@Getter
public class ImageSaveRequestDto {
    @ApiModelProperty(value = "저장된 주문서 id")
    private Long orderId;

    @ApiModelProperty(value = "파일 레포 주소")
    private String storedFilePath;

    public ImageSaveRequestDto(String img, Long id) {
        this.orderId = id;
        this.storedFilePath = img;
    }

    public Image toEntity() {
        return Image.builder()
                .orderId(orderId)
                .originalFileName("")
                .storedFilePath(storedFilePath)
                .fileSize(0L)
                .build();
    }
}
