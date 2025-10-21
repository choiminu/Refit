package com.refit.auth.presentation;

import static org.springframework.http.HttpStatus.OK;

import com.refit.auth.application.dto.LoginRequest;
import com.refit.auth.application.service.AuthService;
import com.refit.common.api.SuccessResponse;
import com.refit.common.session.LoginUser;
import com.refit.common.session.SessionManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "📗 AuthController", description = "Authentication API EndPoint")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final SessionManager sessionManager;

    @Operation(summary = "로그인", description = "주어진 로그인 방식(provider)에 따라 사용자의 인증을 수행합니다.")
    @PostMapping("/login/{provider}")
    public SuccessResponse<LoginUser> login(
            @PathVariable("provider") String provider,
            @RequestBody LoginRequest loginRequest,
            HttpServletRequest request
    ) {
        LoginUser session = authService.login(provider, loginRequest);
        sessionManager.create(request, session);
        return SuccessResponse.success(OK, session);
    }
}
