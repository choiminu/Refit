package com.refit.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입된 이메일 입니다"),
    USER_PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다");

    private final HttpStatus status;
    private final String message;

}
