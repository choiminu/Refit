package com.refit.auth.domain.execption;

import com.refit.common.execption.CustomException;
import com.refit.common.execption.ErrorCode;
import lombok.Getter;

@Getter
public class AuthorizationException extends CustomException {
    public AuthorizationException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public AuthorizationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public AuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public ErrorCode getErrorCode() {
        return super.getErrorCode();
    }
}