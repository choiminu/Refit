package com.refit.user.application.service;

import static com.refit.common.execption.ErrorCode.USER_DUPLICATE_EMAIL;
import static com.refit.common.execption.ErrorCode.USER_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.refit.user.application.dto.UserResponse;
import com.refit.user.domain.User;
import com.refit.user.domain.exception.UserException;
import com.refit.user.domain.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {

    @Mock
    UserMapper userMapper;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserQueryService userQueryService;

    @Test
    @DisplayName("유저 테이블에서 동일한 이메일이 존재하는 경우 예외가 발생한다.")
    void givenDuplicatedEmail_whenValidateEmailNotExists_thenThrowException() {
        //given
        String duplicationEmail = "admin@gmail.com";

        when(userRepository.existsUsersByEmail(duplicationEmail)).thenReturn(true);

        //when & then
        assertThatThrownBy(() -> userQueryService.validateEmailNotExists(duplicationEmail))
                .isInstanceOf(UserException.class).hasMessage(USER_DUPLICATE_EMAIL.getMessage());
    }

    @Test
    @DisplayName("사용자의 이메일로 회원을 검색한다.")
    public void findUserByEmail() {
        //given
        String email = "exam@gmail.com";
        User user = User.builder().email(email).build();
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));

        //when
        User findUser = userQueryService.findByEmail(email);

        //then
        assertThat(findUser.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("테이블에 사용자의 이메일이 존재하지 않는 경우 예외 발생")
    public void findUserByEmail_fail() {
        //given
        String email = "exam@gmail.com";
        User user = User.builder().email(email).build();
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.empty());

        //when & then
        assertThatThrownBy(() -> userQueryService.findByEmail(email))
                .isInstanceOf(UserException.class);
    }

    @Test
    @DisplayName("회원 ID로 회원 정보를 조회할 수 있다.")
    public void findUserById_success() {
        //given
        Long userId = 1L;
        User user = new User(1L, "exam", "test");

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userMapper.entityToResponse(any())).thenReturn(new UserResponse(1L, "email"));

        //when
        UserResponse findUser = userQueryService.findResponseById(userId);

        //then
        assertThat(findUser.getId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("회원 ID가 존재하지 않는 경우 예외가 발생한다.")
    public void findUserById_fail() {
        //given & when & then
        assertThatThrownBy(() -> userQueryService.findResponseById(any())).isInstanceOf(UserException.class).hasMessage(
                USER_NOT_FOUND.getMessage());
    }
}