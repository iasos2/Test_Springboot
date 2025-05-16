package com.study.dto.User.response;

import com.study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignInResponseDto {
    private String token;
    private String username;
    private String role;
}
