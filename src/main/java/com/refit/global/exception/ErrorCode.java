package com.refit.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // User
    USER_DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입된 이메일 입니다"),
    USER_PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다"),
    USER_LOGIN_FAIL(HttpStatus.BAD_REQUEST, "이메일 또는 비밀번호가 일치하지 않습니다."),
    USER_NOT_FOUND_EMAIL(HttpStatus.BAD_REQUEST, "존재하지 않은 이메일입니다."),
    LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED, "로그인이 필요한 요청입니다."),

    // File
    FILE_SAVE_FAIL(HttpStatus.BAD_REQUEST, "파일 업로드에 실패하였습니다.");

    private final HttpStatus status;
    private final String message;

}
