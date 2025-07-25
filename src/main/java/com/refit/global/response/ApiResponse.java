package com.refit.global.response;

import com.refit.global.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private final boolean success;
    private final int statusCode;
    private final T data;
    private final String message;
    private final String errorCode;

    private ApiResponse(boolean success, HttpStatus status, T data, String message, String errorCode) {
        this.success = success;
        this.statusCode = status.value();
        this.data = data;
        this.message = message;
        this.errorCode = errorCode;
    }

    // 성공 응답
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, HttpStatus.OK, data, "요청에 성공하였습니다.", null);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, HttpStatus.OK, data, message, null);
    }

    // 실패 응답
    public static <T> ApiResponse<T> fail(HttpStatus status, String message, String errorCode) {
        return new ApiResponse<>(false, status, null, message, errorCode);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(false, errorCode.getStatus(), null, errorCode.getMessage(), errorCode.name());
    }

}
