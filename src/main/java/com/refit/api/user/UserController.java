package com.refit.api.user;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.api.user.dto.UserSignupResponse;
import com.refit.domain.user.entity.type.LoginType;
import com.refit.domain.user.service.UserService;
import com.refit.global.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "이메일, 비밀번호를 입력받아 신규 사용자를 등록합니다.")
    @PostMapping
    public ApiResponse<UserSignupResponse> signup(@RequestBody UserSignupRequest request) {
        UserSignupResponse response = userService.signup(request, LoginType.LOCAL);
        return ApiResponse.success(HttpStatus.CREATED, "회원가입이 완료되었습니다.", response);
    }

}
