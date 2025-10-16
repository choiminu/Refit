package com.refit.user.application.service;

import static com.refit.common.execption.ErrorCode.USER_DUPLICATE_EMAIL;
import static com.refit.common.execption.ErrorCode.USER_NOT_FOUND;

import com.refit.user.domain.User;
import com.refit.user.domain.UserException;
import com.refit.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }

    public void validateEmailNotExists(String email) {
        if (userRepository.existsUsersByEmail(email)) {
            throw new UserException(USER_DUPLICATE_EMAIL);
        }
    }

}
