package com.refit.domain.user.service.signup;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.domain.user.entity.User;

public interface UserSignupService {
    User signup(UserSignupRequest request);
    void validateUserNotExists(String email);
}
