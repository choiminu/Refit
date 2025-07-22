package com.refit.domain.user.service;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.api.user.dto.UserSignupResponse;
import com.refit.domain.user.entity.User;
import com.refit.domain.user.repository.UserRepository;
import com.refit.domain.user.service.signup.UserSignupService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final Map<String, UserSignupService> userSignupServiceMap;
    private final UserRepository userRepository;

    public UserSignupResponse signup(UserSignupRequest request, String provider) {

        User user = null;

        if (provider == null) {
            user = userSignupServiceMap.get("localSignupService").signup(request);
        }

        User result = userRepository.signup(user);

        return UserSignupResponse.builder()
                .email(result.getEmail())
                .nickname(result.getNickname())
                .build();
    }

}
