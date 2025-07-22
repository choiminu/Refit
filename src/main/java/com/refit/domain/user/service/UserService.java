package com.refit.domain.user.service;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.api.user.dto.UserSignupResponse;
import com.refit.domain.user.entity.Role;
import com.refit.domain.user.entity.User;
import com.refit.domain.user.repository.UserRepository;
import com.refit.global.exception.CustomException;
import com.refit.global.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserSignupResponse signup(UserSignupRequest request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .role(Role.USER)
                .createdDate(LocalDateTime.now())
                .build();

        existsByEmail(user.getEmail());
        User result = userRepository.signup(user);

        UserSignupResponse response = UserSignupResponse.builder()
                .email(result.getEmail())
                .nickname(result.getNickname())
                .build();

        return response;

    }

    private void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.USER_DUPLICATE_EMAIL);
        }
    }


}
