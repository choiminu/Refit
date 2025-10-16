package com.refit.common.execption;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 사용자 관련 에러 코드
    USER_DUPLICATE_EMAIL(400, "이미 가입된 이메일 입니다."),
    USER_PASSWORD_MISMATCH(400, "비밀번호가 일치하지 않습니다.");

    private final int statusCode;
    private final String message;
}