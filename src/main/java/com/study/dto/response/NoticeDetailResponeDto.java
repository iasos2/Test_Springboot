package com.study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NoticeDetailResponeDto {
    private Long id;
    private String title;
    private String content;
}
