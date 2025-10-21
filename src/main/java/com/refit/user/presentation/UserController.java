package com.refit.user.presentation;

import static com.refit.common.api.SuccessResponse.success;

import com.refit.common.api.SuccessResponse;
import com.refit.common.session.LoginUser;
import com.refit.common.session.SessionManager;
import com.refit.user.application.dto.UserResponse;
import com.refit.user.application.dto.UserSignupRequest;
import com.refit.user.application.service.UserCommandService;
import com.refit.user.application.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final UserQueryService userQueryService;
    private final SessionManager sessionManager;

    @Operation(summary = "회원가입", description = "이메일과 패스워드를 기반으로 회원가입을 합니다.")
    @PostMapping
    public SuccessResponse<Long> signup(@RequestBody UserSignupRequest req) {
        return success(HttpStatus.CREATED, userCommandService.signup(req));
    }

    @GetMapping
    public SuccessResponse<UserResponse> findUserById(HttpServletRequest request) {
        LoginUser userSession = sessionManager.getUserSession(request);
        UserResponse res = userQueryService.findById(userSession.getUserId());
        return SuccessResponse.success(HttpStatus.OK, res);
    }

}
