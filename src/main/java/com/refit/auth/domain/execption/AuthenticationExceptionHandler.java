package com.refit.auth.domain.execption;

import com.refit.common.api.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> authExceptionHandler(AuthenticationException ex) {
        return ResponseEntity.status(ex.getErrorCode().getStatusCode()).body(ErrorResponse.error(ex.getErrorCode()));
    }

}
