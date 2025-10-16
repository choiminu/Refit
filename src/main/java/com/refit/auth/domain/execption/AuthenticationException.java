package com.refit.auth.domain.execption;

import com.refit.common.execption.CustomException;
import com.refit.common.execption.ErrorCode;
import lombok.Getter;

@Getter
public class AuthenticationException extends CustomException {
    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthenticationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public AuthenticationException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}