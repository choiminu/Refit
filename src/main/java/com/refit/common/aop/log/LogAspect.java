package com.refit.common.aop.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    public static long SLOW_QUERY_THRESHOLD = 1000;

    @Pointcut("execution(* com.refit..*Service.*(..))")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object logServiceTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long elapsed = end - start;

        String methodSignature = joinPoint.getSignature().toShortString();

        if (elapsed > SLOW_QUERY_THRESHOLD) {
            log.warn("\n🔴 [SLOW QUERY DETECTED] 🔴\n" +
                            "┣━━ Method : {}\n" +
                            "┗━━ Execution Time : {} ms\n",
                    methodSignature, elapsed);
        } else {
            log.info("\n🟢 [QUERY EXECUTION] 🟢\n" +
                            "┣━━ Method : {}\n" +
                            "┗━━ Execution Time : {} ms\n",
                    methodSignature, elapsed);
        }

        return result;
    }

}
