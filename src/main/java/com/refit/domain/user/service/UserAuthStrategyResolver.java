package com.refit.domain.user.service;

import com.refit.domain.user.entity.type.LoginType;
import com.refit.domain.user.service.signup.UserSignupService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthStrategyResolver {

    private final Map<String, UserSignupService> strategyMap;

    public UserSignupService resolve(LoginType loginType) {
        return strategyMap.get(loginType.name().toLowerCase() + "SignupService");
    }

}
