package com.study.controller;

import com.study.common.ApiMappingPattern;
import com.study.dto.request.PostCreateRequestDto;
import com.study.dto.request.PostUpdateRequestDto;
import com.study.dto.response.PostDetailResponseDto;
import com.study.dto.response.PostListResponseDto;
import com.study.dto.response.ResponseDto;
import com.study.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.POST_API)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<ResponseDto<PostDetailResponseDto>> createPost(@Valid @RequestBody PostCreateRequestDto dto) {
        ResponseDto<PostDetailResponseDto> post = postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PostDetailResponseDto>> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostUpdateRequestDto dto
    ) {
        ResponseDto<PostDetailResponseDto> response = postService.updatePost(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<PostListResponseDto>>> getPosts() {
        ResponseDto<List<PostListResponseDto>> posts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PostDetailResponseDto>> getPostById(@PathVariable Long id) {
        ResponseDto<PostDetailResponseDto> post = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }
}
