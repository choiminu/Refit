package com.refit.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignupRequest {
    private String email;
    private String password;
    private String confirmPassword;
}
