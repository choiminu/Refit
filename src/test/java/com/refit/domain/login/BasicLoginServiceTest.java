package com.refit.domain.login;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.refit.api.login.dto.UserLoginRequest;
import com.refit.domain.login.service.BasicLoginService;
import com.refit.domain.user.entity.User;
import com.refit.domain.user.entity.User.UserBuilder;
import com.refit.domain.user.exception.UserException;
import com.refit.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class BasicLoginServiceTest {

    @Mock
    UserService userService;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    BasicLoginService loginService;

    User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("test@naver.com")
                .password("test")
                .build();
    }

    @Test
    void 로그인_성공시_세션이_생성된다() {
        //given
        MockHttpServletResponse response = new MockHttpServletResponse();

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("test@naver.com");
        loginRequest.setPassword("test");

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        when(userService.findUserByEmail(any())).thenReturn(user);
        when(passwordEncoder.matches(eq("test"), any())).thenReturn(true);


        //when
        loginService.login(request, loginRequest.getEmail(), loginRequest.getPassword());

        //then
        HttpSession session = request.getSession(false);
        Assertions.assertThat(session).isNotNull();
    }

    @Test
    void 로그인_실패시_예외가_발생한다() {

        //given
        MockHttpServletRequest request = new MockHttpServletRequest();

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setEmail("test@naver.com");
        loginRequest.setPassword("test!");

        when(userService.findUserByEmail(any())).thenReturn(user);
        when(passwordEncoder.matches(eq("test!"), any())).thenReturn(false);

        //when && then
        Assertions.assertThatThrownBy(() -> loginService.login(request, loginRequest.getEmail(), loginRequest.getPassword()))
                .isInstanceOf(UserException.class);

    }

}
