package com.study.service;

import com.study.dto.request.NoticeCreateRequestDto;
import com.study.dto.response.NoticeDetailResponeDto;
import com.study.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface NoticeService {
    ResponseDto<NoticeDetailResponeDto> createNotice(@Valid NoticeCreateRequestDto dto);
}
