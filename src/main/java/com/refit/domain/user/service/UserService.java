package com.refit.domain.user.service;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.api.user.dto.UserSignupResponse;
import com.refit.domain.user.entity.type.LoginType;
import com.refit.domain.user.entity.User;
import com.refit.domain.user.repository.UserRepository;
import com.refit.domain.user.service.signup.UserSignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserAuthStrategyResolver resolver;

    public UserSignupResponse signup(UserSignupRequest request, LoginType type) {
        UserSignupService strategy = resolver.resolve(type);
        User user = strategy.signup(request);
        User result = userRepository.signup(user);

        return UserSignupResponse.builder()
                .nickname(result.getNickname())
                .build();
    }

}
