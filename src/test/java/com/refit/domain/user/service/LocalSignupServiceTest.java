package com.refit.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.refit.api.user.dto.UserSignupRequest;
import com.refit.domain.user.entity.vo.Nickname;
import com.refit.domain.user.entity.type.Role;
import com.refit.domain.user.entity.User;
import com.refit.domain.user.exception.UserException;
import com.refit.domain.user.repository.UserRepository;
import com.refit.domain.user.service.signup.LocalSignupService;
import com.refit.global.exception.ErrorCode;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@ExtendWith(MockitoExtension.class)
class LocalSignupServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private LocalSignupService localSignupService;

    private UserSignupRequest request;
    private User user;

    @BeforeEach
    void setUp() {

        request = UserSignupRequest.builder()
                .email("test@example.com")
                .password("secure1234")
                .confirmPassword("secure1234")
                .build();

        user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(new Nickname("test"))
                .role(Role.USER)
                .createdDate(LocalDateTime.now())
                .build();
    }

    @Test
    void 회원가입_성공시_이메일과_닉네임을_반환한다() {
        User response = localSignupService.signup(request);

        assertThat(response.getEmail()).isEqualTo(request.getEmail());
    }

    @Test
    void 중복된_이메일이_존재하는_경우_예외가_발생한다() {

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThatThrownBy(() -> localSignupService.signup(request))
                .isInstanceOf(UserException.class)
                .hasMessage(ErrorCode.USER_DUPLICATE_EMAIL.getMessage());
    }

    @Test
    void 회원가입시_비밀번호가_일치하지_않으면_예외가_발생한다() {
        //given
        request.setPassword("pw1");
        request.setConfirmPassword("pw2");

        //when && then
        assertThatThrownBy(() -> localSignupService.signup(request))
                .isInstanceOf(UserException.class)
                        .hasMessage(ErrorCode.USER_PASSWORD_MISMATCH.getMessage());
    }

}