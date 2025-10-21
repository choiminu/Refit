package com.refit.auth.application.strategy;

import static com.refit.common.execption.ErrorCode.LOGIN_FAIL;

import com.refit.auth.domain.LoginProvider;
import com.refit.auth.application.dto.LoginRequest;
import com.refit.auth.domain.execption.AuthenticationException;
import com.refit.common.session.LoginUser;
import com.refit.user.application.service.UserQueryService;
import com.refit.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordAuthStrategy implements AuthStrategy{

    private final UserQueryService userQueryService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean support(String provider) {
        return LoginProvider.SERVER == LoginProvider.from(provider);
    }

    @Override
    public LoginUser authentication(LoginRequest request) {
        final String email = request.getEmail();
        final String password = request.getPassword();

        User findUser = userQueryService.findByEmail(email);
        if (!(passwordEncoder.matches(password, findUser.getPassword()))) {
            throw new AuthenticationException(LOGIN_FAIL);
        }

        return new LoginUser(findUser.getId());
    }
}
