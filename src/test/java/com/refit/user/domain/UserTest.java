package com.refit.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("이메일과 패스워드를 파라미터로 받아 회원 객체를 생성한다.")
    void createUser() {
        //given
        String email = "test@gmail.com";
        String password = "root1234";

        //when
        User user = User.builder()
                .email(email)
                .password(password)
                .build();

        //then
        Assertions.assertThat(user.getEmail()).isEqualTo(email);
        Assertions.assertThat(user.getPassword()).isEqualTo(password);
    }
}