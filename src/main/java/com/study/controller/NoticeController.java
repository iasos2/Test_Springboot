package com.study.controller;

import com.study.common.ApiMappingPattern;
import com.study.dto.request.NoticeCreateRequestDto;
import com.study.dto.response.NoticeDetailResponeDto;
import com.study.dto.response.ResponseDto;
import com.study.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.NOTICE_API)
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<ResponseDto> createNotice(@Valid @RequestBody NoticeCreateRequestDto dto) {
        ResponseDto<NoticeDetailResponeDto> notice = noticeService.createNotice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(notice);
    }
}
