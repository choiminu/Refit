package com.refit.user.application.service;

import static com.refit.common.execption.ErrorCode.USER_DUPLICATE_EMAIL;
import static org.mockito.Mockito.when;

import com.refit.user.domain.UserException;
import com.refit.user.domain.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {

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
        Assertions.assertThatThrownBy(() -> userQueryService.validateEmailNotExists(duplicationEmail))
                .isInstanceOf(UserException.class).hasMessage(USER_DUPLICATE_EMAIL.getMessage());
    }
}