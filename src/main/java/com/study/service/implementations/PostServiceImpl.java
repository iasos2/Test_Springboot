package com.study.service.implementations;

import com.study.common.ResponseMessage;
import com.study.dto.request.PostCreateRequestDto;
import com.study.dto.request.PostUpdateRequestDto;
import com.study.dto.response.PostDetailResponseDto;
import com.study.dto.response.PostListResponseDto;
import com.study.dto.response.ResponseDto;
import com.study.entity.Post;
import com.study.repository.PostRepository;
import com.study.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public ResponseDto<PostDetailResponseDto> createPost(PostCreateRequestDto dto) {
        PostDetailResponseDto responseDto = null;

        Post newPost = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        Post savedPost = postRepository.save(newPost);

        responseDto = PostDetailResponseDto.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional
    public ResponseDto<PostDetailResponseDto> updatePost(Long id, PostUpdateRequestDto dto) {
        PostDetailResponseDto responseDto = null;

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        Post updatedPost = postRepository.save(post);

        responseDto = PostDetailResponseDto.builder()
                .id(updatedPost.getId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<PostListResponseDto>> getAllPosts() {
        List<PostListResponseDto> responseDtos = null;

        List<Post> posts = postRepository.findAll();

        responseDtos = posts.stream()
                .map(post-> PostListResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toUnmodifiableList());

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDtos);
    }

    @Override
    public ResponseDto<PostDetailResponseDto> getPostById(Long id) {
        PostDetailResponseDto responseDto = null;

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_POST + id));

        responseDto = PostDetailResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }
}
