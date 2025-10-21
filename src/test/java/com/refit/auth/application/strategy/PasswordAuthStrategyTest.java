package com.refit.auth.application.strategy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.refit.auth.application.dto.LoginRequest;
import com.refit.auth.domain.execption.AuthenticationException;
import com.refit.common.session.LoginUser;
import com.refit.user.application.service.UserQueryService;
import com.refit.user.domain.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class PasswordAuthStrategyTest {

    @Mock
    UserQueryService userQueryService;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    PasswordAuthStrategy passwordAuthStrategy;

    @Test
    @DisplayName("로그인 제공자가 SERVER인 경우 True를 반환한다.")
    public void support() {
        //given
        String provider = "server";

        //when
        boolean support = passwordAuthStrategy.support(provider);

        //then
        Assertions.assertThat(support).isTrue();
    }

    @Test
    @DisplayName("이메일과 비밀번호가 일치하면 로그인에 성공한다")
    public void login_success() {
        //given
        LoginRequest request = new LoginRequest("test", "exam");

        when(userQueryService.findByEmail(any())).thenReturn(new User(1L, "test", "exam"));
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        //when
        LoginUser loginUser = passwordAuthStrategy.authentication(request);

        //then
        Assertions.assertThat(loginUser.getUserId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("비밀번호가 일치하지 않으면 예외가 발생한다.")
    public void login_fail() {
        //given
        LoginRequest request = new LoginRequest("test", "exam");

        when(userQueryService.findByEmail(any())).thenReturn(new User(1L, "test", "exam"));
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        //when & then
        Assertions.assertThatThrownBy(() -> passwordAuthStrategy.authentication(request))
                .isInstanceOf(AuthenticationException.class);
    }
    @Test
    @DisplayName("로그인 시 마지막 로그인 시간이 현재 시각으로 갱신된다")
    public void update_loginAt() {
        //given
        LoginRequest req = new LoginRequest("test", "test");
        User user = new User("user", "pass");
        user.updateLoginTime();

        when(userQueryService.findByEmail(any())).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        //when
        passwordAuthStrategy.authentication(req);

        //then
        Assertions.assertThat(user.getLastLoginAt().toLocalDate()).isEqualTo(LocalDate.now());
    }

}