package com.refit.domain.user.service.signup;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.domain.user.entity.Point;
import com.refit.domain.user.entity.Role;
import com.refit.domain.user.entity.User;
import com.refit.domain.user.exception.UserException;
import com.refit.domain.user.repository.UserRepository;
import com.refit.global.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalSignupService implements UserSignupService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User signup(UserSignupRequest request) {

        validateUserNotExists(request.getEmail());

        return User.builder()
                .email(request.getEmail())
                .password(verifyPassword(request.getPassword(), request.getConfirmPassword()))
                .nickname(request.getNickname())
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
