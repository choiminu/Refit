package com.refit.domain.user.service;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.api.user.dto.UserSignupResponse;
import com.refit.domain.user.entity.Role;
import com.refit.domain.user.entity.User;
import com.refit.domain.user.exception.UserException;
import com.refit.domain.user.repository.UserRepository;
import com.refit.global.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserSignupResponse signup(UserSignupRequest request) {

        String password = verifyPassword(request.getPassword(), request.getConfirmPassword());

        User user = User.builder()
                .email(request.getEmail())
                .password(password)
                .nickname(request.getNickname())
                .role(Role.USER)
                .createdDate(LocalDateTime.now())
                .build();

        existsByEmail(user.getEmail());
        User result = userRepository.signup(user);

        return UserSignupResponse.builder()
                .email(result.getEmail())
                .nickname(result.getNickname())
                .build();
    }

    private void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserException(ErrorCode.USER_DUPLICATE_EMAIL);
        }
    }

    private String verifyPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new UserException(ErrorCode.USER_PASSWORD_MISMATCH);
        }
        return passwordEncoder.encode(password);
    }

}
