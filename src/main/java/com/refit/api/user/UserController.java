package com.refit.api.user;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.api.user.dto.UserSignupResponse;
import com.refit.domain.user.service.UserService;
import com.refit.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserSignupResponse> localJoin(@ModelAttribute @ParameterObject UserSignupRequest request) {
        return ApiResponse.success(201, "가입완료", userService.signup(request, null));
    }

}
