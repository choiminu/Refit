package com.refit.global.api;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    public static final String SUCCESS_MESSAGE = "요청에 성공하였습니다.";
    public static final String FAIL_MESSAGE = "요청에 실패하였습니다.";

    private final boolean success;
    private final T data;
    private final String message;

    private ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // 성공
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true, SUCCESS_MESSAGE, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, SUCCESS_MESSAGE, data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }


    // 실패
    public static <T> ApiResponse<T> fail() {
        return new ApiResponse<>(false, FAIL_MESSAGE, null);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(false, message, null);
    }

    public static <T> ApiResponse<T> fail(String message, T data) {
        return new ApiResponse<>(false, message, data);
    }

}
