package com.study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String content;
}
