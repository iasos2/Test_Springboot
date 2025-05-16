package com.study.service.implementations;

import com.study.common.ResponseMessage;
import com.study.dto.response.ResponseDto;
import com.study.dto.User.response.GetUserResponseDto;
import com.study.entity.User;
import com.study.repository.UserRepository;
import com.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseDto<GetUserResponseDto> getUserInfo(String userName) {
        User user = userRepository.findByUserName(userName).orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }

        GetUserResponseDto data = new GetUserResponseDto(user);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
