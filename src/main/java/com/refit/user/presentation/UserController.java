package com.refit.user.presentation;

import static com.refit.common.api.SuccessResponse.success;
import static org.springframework.http.HttpStatus.*;

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
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping
    @Operation(summary = "회원가입", description = "이메일과 패스워드를 기반으로 회원가입을 합니다.")
    public SuccessResponse<Long> signup(@RequestBody UserSignupRequest req) {
        return success(CREATED, userCommandService.signup(req));
    }

    @GetMapping("/me")
    @Operation(summary = "회원 정보 조회", description = "현재 로그인한 사용자의 세션 정보를 기반으로 회원 정보 조회합니다.")
    public SuccessResponse<UserResponse> me(HttpServletRequest request) {
        LoginUser userSession = sessionManager.getUserSession(request);
        UserResponse res = userQueryService.findResponseById(userSession.getUserId());
        return SuccessResponse.success(OK, res);
    }

    @DeleteMapping
    @Operation(summary = "회원탈퇴", description = "현재")
    public SuccessResponse<Void> softDelete(HttpServletRequest req) {
        userCommandService.delete(sessionManager.getUserSession(req).getUserId());
        return SuccessResponse.success(OK);
    }

}
