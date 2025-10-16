package com.refit.auth.application.service;

import com.refit.auth.application.dto.LoginRequest;
import com.refit.auth.domain.execption.AuthenticationException;
import com.refit.auth.application.strategy.AuthStrategy;
import com.refit.common.execption.ErrorCode;
import com.refit.common.session.LoginUser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final List<AuthStrategy> authStrategies;

    public LoginUser login(String provider, LoginRequest request) {
        return authStrategies.stream()
                .filter(auth -> auth.support(provider))
                .findFirst()
                .orElseThrow(() -> new AuthenticationException(ErrorCode.UNSUPPORTED_LOGIN_PROVIDER))
                .authentication(request);
    }

}
