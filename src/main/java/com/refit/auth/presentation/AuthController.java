package com.refit.auth.presentation;

import static org.springframework.http.HttpStatus.OK;

import com.refit.auth.application.dto.LoginRequest;
import com.refit.auth.application.service.AuthService;
import com.refit.common.api.SuccessResponse;
import com.refit.common.session.LoginUser;
import com.refit.common.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final SessionManager sessionManager;

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
