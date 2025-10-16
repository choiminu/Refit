package com.refit.common.session;

import com.refit.auth.domain.execption.AuthorizationException;
import com.refit.common.execption.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

    public static final String USER_SESSION = "userSession";
    public static final int MAX_INACTIVE_INTERVAL_SECONDS = 1800;

    public void create(HttpServletRequest request, LoginUser user) {
        HttpSession old = request.getSession(false);
        if (old != null) {
            old.invalidate();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(USER_SESSION, user);
        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL_SECONDS);
    }

    public LoginUser getUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(USER_SESSION) == null) {
            throw new AuthorizationException(ErrorCode.UNAUTHORIZED);
        }

        return (LoginUser) session.getAttribute(USER_SESSION);
    }

    public void invalidate(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}