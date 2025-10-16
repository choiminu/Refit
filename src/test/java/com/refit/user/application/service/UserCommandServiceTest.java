package com.refit.user.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.refit.common.execption.ErrorCode;
import com.refit.user.application.dto.UserSignupRequest;
import com.refit.user.domain.User;
import com.refit.user.domain.UserException;
import com.refit.user.domain.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserCommandServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserQueryService userQueryService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserCommandService userCommandService;

    @Test
    @DisplayName("사용자는 이메일과 패스워드를 기반으로 회원가입을 할 수 있고 회원가입 성공시 사용자의 ID를 반환한다.")
    void when_signup_success_return_userId() {
        //given
        String email = "test@gmail.com";
        String password = "root1234";

        User user = new User(1L, email, password);
        UserSignupRequest request = new UserSignupRequest(email, password, password);

        when(userMapper.signupRequestToEntity(any())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);

        //when
        Long userId = userCommandService.signup(request);

        //then
        assertThat(userId).isEqualTo(1L);
    }

    @Test
    @DisplayName("이미 가입된 이메일로 회원가입을 시도하면 예외가 발생한다.")
    public void givenDuplicatedEmail_whenSignup_thenThrowsUserException() {
        //given
        String email = "test@gmail.com";
        String password = "root1234";

        UserSignupRequest request = new UserSignupRequest(email, password, password);

        doThrow(new UserException(ErrorCode.USER_DUPLICATE_EMAIL))
                .when(userQueryService).validateEmailNotExists(any());

        //when & then
        assertThatThrownBy(() -> userCommandService.signup(request)).isInstanceOf(UserException.class);
    }

    @Test
    @DisplayName("회원가입시 비밀번호와 확인 패스워드가 다르다면 예외가 발생한다.")
    public void whenSignupPasswordMisMath_thenThrowException() {
        //given
        String password = "root1234";
        String confirmPassword = "root";
        UserSignupRequest req = new UserSignupRequest("email", password, confirmPassword);

        //when & then
        assertThatThrownBy(() -> userCommandService.signup(req))
                .isInstanceOf(UserException.class).hasMessage(ErrorCode.USER_PASSWORD_MISMATCH.getMessage());
    }

    @Test
    @DisplayName("회원가입시 패스워드는 암호화되어 저장된다.")
    void passwordEncoding_success() {
        //given
        String email = "test@gmail.com";
        String password = "root";
        String confirmPassword = "root";
        UserSignupRequest req = new UserSignupRequest(email, password, confirmPassword);

        when(userMapper.signupRequestToEntity(any())).thenReturn(new User());
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");

        //when
        userCommandService.signup(req);

        //then
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        User savedUser = captor.getValue();
        Assertions.assertThat(savedUser.getPassword()).isEqualTo("encodedPassword");
    }
}