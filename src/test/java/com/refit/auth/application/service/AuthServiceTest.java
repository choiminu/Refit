package com.refit.auth.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.refit.auth.application.dto.LoginRequest;
import com.refit.auth.application.strategy.AuthStrategy;
import com.refit.auth.application.strategy.PasswordAuthStrategy;
import com.refit.auth.domain.execption.AuthenticationException;
import com.refit.common.session.LoginUser;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    PasswordAuthStrategy passwordAuthStrategy;

    @InjectMocks
    AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService(List.of(passwordAuthStrategy));
    }


    @Test
    @DisplayName("provider에 맞는 인증 전략을 찾아 로그인에 성공한다")
    public void login_success() {
        //given
        LoginRequest req = new LoginRequest("test@example.com", "1234");
        when(passwordAuthStrategy.support("server")).thenReturn(true);
        when(passwordAuthStrategy.authentication(req)).thenReturn(new LoginUser(1L));

        //when
        LoginUser result = authService.login("server", req);

        //then
        Assertions.assertThat(result.getUserId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("지원하지 않는 provider일 경우 AuthenticationException을 던진다")
    public void login_fail() {
        //given
        LoginRequest req = new LoginRequest("test@example.com", "1234");
        when(passwordAuthStrategy.support(any())).thenReturn(false);

        //when & then
        Assertions.assertThatThrownBy(() -> authService.login("github", req))
                .isInstanceOf(AuthenticationException.class);

    }

}