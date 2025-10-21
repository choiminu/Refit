package com.refit.user.domain.exception;

import com.refit.common.api.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> userExceptionHandler(UserException e) {
        return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(ErrorResponse.error(e.getErrorCode()));
    }
}
