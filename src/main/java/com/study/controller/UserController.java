package com.study.controller;

import com.study.common.ApiMappingPattern;
import com.study.dto.response.ResponseDto;
import com.study.dto.User.response.GetUserResponseDto;
import com.study.entity.User;
import com.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.USER_API)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private static final String GET_USER_INFO = "/me";

    @GetMapping(GET_USER_INFO)
    public ResponseEntity<ResponseDto<GetUserResponseDto>> getUserInfo(@AuthenticationPrincipal String userName) {
        ResponseDto<GetUserResponseDto>response = userService.getUserInfo(userName);
        return ResponseEntity.ok(response);
    }
}
