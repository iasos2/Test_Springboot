package com.study.service.implementations;

import com.study.common.ResponseMessage;
import com.study.dto.request.NoticeCreateRequestDto;
import com.study.dto.response.NoticeDetailResponeDto;
import com.study.dto.response.ResponseDto;
import com.study.entity.Notice;
import com.study.repository.NoticeRepository;
import com.study.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public ResponseDto<NoticeDetailResponeDto> createNotice(NoticeCreateRequestDto dto) {
        NoticeDetailResponeDto responseDto = null;

        Notice newNotice = Notice.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();

        Notice saveNotice = noticeRepository.save(newNotice);

        responseDto = NoticeDetailResponeDto.builder()
                .title(saveNotice.getTitle())
                .content(saveNotice.getContent())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }
}
