package com.refit.user.presentation;

import static com.refit.common.api.SuccessResponse.success;

import com.refit.common.api.SuccessResponse;
import com.refit.user.application.dto.UserSignupRequest;
import com.refit.user.application.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserCommandService userCommandService;

    @PostMapping
    public SuccessResponse<Long> signup(@RequestBody UserSignupRequest req) {
        return success(HttpStatus.CREATED, userCommandService.signup(req));
    }

}
