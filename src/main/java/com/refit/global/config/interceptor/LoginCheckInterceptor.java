package com.refit.global.config.interceptor;

import com.refit.domain.user.exception.UserException;
import com.refit.global.exception.ErrorCode;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        log.info("📌 LoginCheckInterceptor 실행 ");

        if (isGetMethod(request)) {
            return true;
        }

        hasLoginSession(request);
        return true;
    }

    private boolean isGetMethod(HttpServletRequest request) {
        return request.getMethod().equals(HttpMethod.GET.name());
    }


    private void hasLoginSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginMember") == null) {
            throw new UserException(ErrorCode.LOGIN_REQUIRED);
        }
    }


}
