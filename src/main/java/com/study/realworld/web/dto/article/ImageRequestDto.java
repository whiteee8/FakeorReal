package com.study.realworld.web.dto.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;


@Getter
public class ImageRequestDto {
    @ApiModelProperty(value = "이미지고유 id")
    private Long id;

    @ApiModelProperty(value = "저장된 주문서 id")
    private Long orderId;

    @ApiModelProperty(value = "고유 파일 이름")
    private String originalFileName;

    @ApiModelProperty(value = "파일 레포 주소")
    private String storedFilePath;

    @ApiModelProperty(value = "파일 사이즈")
    private Long fileSize;

    public ImageRequestDto(Long id, Long orderId, String originalFileName, String storedFilePath, Long fileSize) {
        this.id = id;
        this.orderId = orderId;
        this.originalFileName = originalFileName;
        this.storedFilePath = storedFilePath;
        this.fileSize = fileSize;
    }
}
