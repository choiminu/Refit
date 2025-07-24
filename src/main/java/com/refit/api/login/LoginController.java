package com.refit.api.login;

import com.refit.api.login.dto.UserLoginRequest;
import com.refit.domain.login.service.BasicLoginService;
import com.refit.global.api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final BasicLoginService basicLoginService;

    @PostMapping
    public ApiResponse<Void> login(@RequestBody UserLoginRequest loginRequest, HttpServletRequest request) {
        basicLoginService.login(request, loginRequest.getEmail(), loginRequest.getPassword());
        return ApiResponse.success("로그인 성공");
    }

}
