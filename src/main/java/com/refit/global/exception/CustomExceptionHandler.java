package com.refit.global.exception;

import com.refit.domain.user.exception.UserException;
import com.refit.global.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ApiResponse<Void> handleCustomException(CustomException e) {
        log.error("CustomException : {}", e.getMessage());
        return ApiResponse.fail(HttpStatus.BAD_REQUEST, e.getMessage(), "FAIL");
    }

    @ExceptionHandler(UserException.class)
    public ApiResponse<Void> handleUserException(UserException e) {
        log.error("UserException : {}", e.getMessage());
        return ApiResponse.fail(HttpStatus.BAD_REQUEST, e.getMessage(), "FAIL");
    }


}
