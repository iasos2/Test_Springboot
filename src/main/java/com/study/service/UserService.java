package com.study.service;

import com.study.dto.response.ResponseDto;
import com.study.dto.User.response.GetUserResponseDto;

public interface UserService {
    ResponseDto<GetUserResponseDto> getUserInfo(String userName);
}
