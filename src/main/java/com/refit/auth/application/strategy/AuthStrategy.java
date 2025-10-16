package com.refit.auth.application.strategy;

import com.refit.auth.application.dto.LoginRequest;
import com.refit.common.session.LoginUser;

public interface AuthStrategy {
    boolean support(String provider);
    LoginUser authentication(LoginRequest req);
}
