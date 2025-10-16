package com.refit.auth.domain;

import static com.refit.common.execption.ErrorCode.UNSUPPORTED_LOGIN_PROVIDER;

import com.refit.auth.domain.execption.AuthenticationException;

public enum LoginProvider {
    SERVER, GOOGLE, KAKAO;

    public static LoginProvider from(String provider) {
        for (LoginProvider value : LoginProvider.values()) {
            if (value.name().equalsIgnoreCase(provider)) {
                return value;
            }
        }
        throw new AuthenticationException(UNSUPPORTED_LOGIN_PROVIDER);
    }

}