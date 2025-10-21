package com.refit.user.application.service;

import static com.refit.common.execption.ErrorCode.USER_PASSWORD_MISMATCH;

import com.refit.user.application.dto.UserResponse;
import com.refit.user.application.dto.UserSignupRequest;
import com.refit.user.application.dto.UserUpdateRequest;
import com.refit.user.domain.User;
import com.refit.user.domain.exception.UserException;
import com.refit.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;
    private final UserQueryService userQueryService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public Long signup(UserSignupRequest req) {
        userQueryService.validateEmailNotExists(req.getEmail());

        // TODO : 정적 팩토리 메서드를 생성하여 해당 메서드 내부에서 처리
        validatePasswordMatch(req.getPassword(), req.getConfirmPassword());

        User user = userMapper.signupRequestToEntity(req);
        user.changePassword(passwordEncoder.encode(req.getPassword()));

        userRepository.save(user);
        return user.getId();
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }


    private void validatePasswordMatch(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new UserException(USER_PASSWORD_MISMATCH);
        }
    }
}
