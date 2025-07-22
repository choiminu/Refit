package com.refit.api.user;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.api.user.dto.UserSignupResponse;
import com.refit.domain.user.service.UserService;
import com.refit.global.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<UserSignupResponse>> join(@ModelAttribute UserSignupRequest request) {
        return new ResponseEntity<>(ApiResponse.success(userService.signup(request)),HttpStatus.CREATED);
    }

}
