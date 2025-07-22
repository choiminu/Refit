package com.refit.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.api.user.dto.UserSignupResponse;
import com.refit.domain.user.entity.Role;
import com.refit.domain.user.entity.User;
import com.refit.domain.user.repository.UserRepository;
import com.refit.global.exception.CustomException;
import com.refit.global.exception.ErrorCode;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserSignupRequest request;
    private User user;

    @BeforeEach
    void setUp() {

        request = UserSignupRequest.builder()
                .email("test@example.com")
                .password("secure1234")
                .nickname("tester")
                .build();

        user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .role(Role.USER)
                .createdDate(LocalDateTime.now())
                .build();
    }

    @Test
    void 회원가입_성공시_이메일과_닉네임을_반환한다() {
        //given
        when(userRepository.signup(any(User.class))).thenReturn(user);

        //when
        UserSignupResponse response = userService.signup(request);

        //then
        assertThat(response.getEmail()).isEqualTo(request.getEmail());
        assertThat(response.getNickname()).isEqualTo(request.getNickname());
    }

    @Test
    void 중복된_이메일이_존재하는_경우_예외가_발생한다() {
        //given
        when(userRepository.signup(any(User.class))).thenThrow(new CustomException(ErrorCode.USER_DUPLICATE_EMAIL));

        //when && then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> userService.signup(request))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.USER_DUPLICATE_EMAIL.getMessage());
    }

}