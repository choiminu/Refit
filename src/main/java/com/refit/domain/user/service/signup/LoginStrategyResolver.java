package com.refit.domain.user.service.signup;

import com.refit.domain.user.entity.LoginType;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginStrategyResolver {

    private final Map<String, UserSignupService> strategyMap;

    public UserSignupService resolve(LoginType loginType) {
        return strategyMap.get(loginType.name().toLowerCase() + "SignupService");
    }

}
