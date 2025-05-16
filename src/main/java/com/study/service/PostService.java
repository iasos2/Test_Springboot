package com.study.service;

import com.study.dto.request.PostCreateRequestDto;
import com.study.dto.request.PostUpdateRequestDto;
import com.study.dto.response.PostDetailResponseDto;
import com.study.dto.response.PostListResponseDto;
import com.study.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    ResponseDto<PostDetailResponseDto> createPost(@Valid PostCreateRequestDto dto);

    ResponseDto<PostDetailResponseDto> updatePost(Long id, @Valid PostUpdateRequestDto dto);

    ResponseDto<List<PostListResponseDto>> getAllPosts();

    ResponseDto<PostDetailResponseDto> getPostById(Long id);
}
