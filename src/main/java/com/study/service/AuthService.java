package com.study.service;

import com.study.dto.response.ResponseDto;
import com.study.dto.User.request.UserSignInRequestDto;
import com.study.dto.User.request.UserSignUpRequestDto;
import com.study.dto.User.response.UserSignInResponseDto;
import com.study.dto.User.response.UserSignUpResponseDto;
import jakarta.validation.Valid;

public interface AuthService{
    ResponseDto<UserSignUpResponseDto> signup(@Valid UserSignUpRequestDto dto);
    ResponseDto<UserSignInResponseDto> login(@Valid UserSignInRequestDto dto);
}
