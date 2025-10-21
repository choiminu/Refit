package com.refit.user.presentation;

import static com.refit.common.api.SuccessResponse.success;

import com.refit.common.api.SuccessResponse;
import com.refit.user.application.dto.UserSignupRequest;
import com.refit.user.application.service.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "🚶‍♂️ UserController", description = "User CURD API EndPoint")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserCommandService userCommandService;

    @Operation(summary = "회원가입", description = "이메일과 패스워드를 기반으로 회원가입을 합니다.")
    @PostMapping
    public SuccessResponse<Long> signup(@RequestBody UserSignupRequest req) {
        return success(HttpStatus.CREATED, userCommandService.signup(req));
    }

}
