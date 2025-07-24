package com.refit.domain.file.exception;

import com.refit.global.exception.CustomException;
import com.refit.global.exception.ErrorCode;

public class FileUploadException extends CustomException {

    public FileUploadException(ErrorCode errorCode) {
        super(errorCode);
    }

    public FileUploadException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public FileUploadException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

}
