package com.study.dto.User.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
}
