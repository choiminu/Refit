package com.refit.domain.user.service.signup.strategy;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.domain.user.entity.vo.Nickname;
import com.refit.domain.user.entity.vo.Point;
import com.refit.domain.user.entity.type.Role;
import com.refit.domain.user.entity.User;
import com.refit.domain.user.exception.UserException;
import com.refit.domain.user.repository.UserRepository;
import com.refit.domain.user.service.signup.UserSignupService;
import com.refit.global.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalSignup implements UserSignupService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User signup(UserSignupRequest request) {

        validateUserNotExists(request.getEmail());

        return User.builder()
                .email(request.getEmail())
                .password(verifyPassword(request.getPassword(), request.getConfirmPassword()))
                .nickname(new Nickname())
                .role(Role.USER)
                .createdDate(LocalDateTime.now())
                .point(new Point(0))
                .build();
    }

    @Override
    public void validateUserNotExists(String email) {
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
