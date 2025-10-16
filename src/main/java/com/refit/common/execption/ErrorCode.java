package com.refit.common.execption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 인증 관련 에러
    LOGIN_FAIL(400, "로그인 실패"),
    UNAUTHORIZED(400,  "로그인이 필요한 서비스입니다."),
    FORBIDDEN(400, "접근 권한이 부족합니다."),
    UNSUPPORTED_LOGIN_PROVIDER(400, "잘못된 로그인 제공자입니다."),

    // 사용자 관련 에러 코드
    USER_NOT_FOUND(400, "사용자 정보를 찾을 수 없습니다."),
    USER_DUPLICATE_EMAIL(400, "이미 가입된 이메일 입니다."),
    USER_PASSWORD_MISMATCH(400, "비밀번호가 일치하지 않습니다.");

    private final int statusCode;
    private final String message;
}