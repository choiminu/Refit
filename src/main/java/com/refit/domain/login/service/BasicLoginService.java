package com.refit.domain.login.service;

import com.refit.domain.user.entity.User;
import com.refit.domain.user.exception.UserException;
import com.refit.domain.user.service.UserService;
import com.refit.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BasicLoginService {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public void login(HttpServletRequest request, String email, String password) {
        User findUser = userService.findUserByEmail(email);
        passwordVerification(password, findUser.getPassword());

        log.info("📌로그인 성공 email = {} password = {}", email, password);
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", findUser);
    }

    private void passwordVerification(String rawPassword, String password) {
        if (!passwordEncoder.matches(rawPassword, password)) {
            throw new UserException(ErrorCode.USER_LOGIN_FAIL);
        }
    }


}
