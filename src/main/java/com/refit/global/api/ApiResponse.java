package com.refit.global.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    public static final String SUCCESS_MESSAGE = "요청에 성공하였습니다.";
    public static final String FAIL_MESSAGE = "요청에 실패하였습니다.";

    private final int statusCode;
    private final T data;
    private final String message;

    private ApiResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    // 성공
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(HttpStatus.OK.value(), SUCCESS_MESSAGE, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), SUCCESS_MESSAGE, data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), message, data);
    }

    public static <T> ApiResponse<T> success(int statusCode, String message, T data) {
        return new ApiResponse<>(statusCode, message, data);
    }


    // 실패
    public static <T> ApiResponse<T> fail() {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), FAIL_MESSAGE, null);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    public static <T> ApiResponse<T> fail(String message, T data) {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), message, data);
    }

    public static <T> ApiResponse<T> fail(int statusCode, String message, T data) {
        return new ApiResponse<>(statusCode, message, data);
    }

}
