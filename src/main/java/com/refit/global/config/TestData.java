//package com.refit.global.config;
//
//import com.refit.domain.user.entity.User;
//import com.refit.domain.user.repository.UserRepository;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class TestData implements ApplicationRunner {
//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    @Override
//    @Transactional
//    public void run(ApplicationArguments args) throws Exception {
//
//        log.info("테스트 데이터 생성");
//
//        User user = User.builder()
//                .email("string")
//                .password(passwordEncoder.encode("string"))
//                .build();
//
//        userRepository.signup(user);
//    }
//
//
//}
