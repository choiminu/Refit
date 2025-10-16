package com.refit.user.domain;

import com.refit.common.execption.CustomException;
import com.refit.common.execption.ErrorCode;

public class UserException extends CustomException {
    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public UserException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
