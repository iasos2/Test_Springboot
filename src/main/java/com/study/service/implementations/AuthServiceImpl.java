package com.study.service.implementations;

import com.study.common.ResponseMessage;
import com.study.dto.response.ResponseDto;
import com.study.dto.User.request.UserSignInRequestDto;
import com.study.dto.User.request.UserSignUpRequestDto;
import com.study.dto.User.response.UserSignInResponseDto;
import com.study.dto.User.response.UserSignUpResponseDto;
import com.study.entity.User;
import com.study.provider.JwtProvider;
import com.study.repository.UserRepository;
import com.study.service.AuthService;
import com.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;


    @Override
    public ResponseDto<UserSignUpResponseDto> signup(UserSignUpRequestDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        if (userRepository.existsByUserName(username)) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USERNAME);
        }

        String encodePassword = bCryptPasswordEncoder.encode(password);

        User user = User.builder()
                .userName(username)
                .password(encodePassword)
                .role("USER") // 기본 역할
                .build();

        UserSignUpResponseDto responseDto = new UserSignUpResponseDto(
                user.getUserName(),
                user.getRole()
        );

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<UserSignInResponseDto> login(UserSignInRequestDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        User user = userRepository.findByUserName(username)
                .orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        String token = jwtProvider.generateJwtToken(user.getUserName(), user.getRole());

        UserSignInResponseDto responseDto = new UserSignInResponseDto(
                token,
                user.getUserName(),
                user.getRole()
        );

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }
}
