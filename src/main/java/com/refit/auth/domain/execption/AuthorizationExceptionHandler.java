package com.refit.auth.domain.execption;

import com.refit.common.api.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthorizationExceptionHandler {
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> authorizationExceptionHandler(AuthorizationException e) {
        return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(ErrorResponse.error(e.getErrorCode()));
    }
}
