package com.refit.domain.user.entity.type;

public enum LoginType {
    LOCAL, GOOGLE, KAKAO;

    public String toSignupServiceName() {
        return name().toLowerCase() + "Signup";
    }
}
