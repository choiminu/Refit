package com.refit.common.api;

import com.refit.common.execption.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String message;

    public static ErrorResponse error(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatusCode(),errorCode.getMessage());
    }
}