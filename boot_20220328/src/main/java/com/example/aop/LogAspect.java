package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // 공통적인 작업을 구현하기 위한용도
    @Around("execution(* com.example.controller.*Controller.*(..)) or execution(* com.example.mapper.*Mapper.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String type = "";
        if (className.contains("Controller") == true) {
            type = "Controller => ";
        } else if (className.contains("Mapper") == true) {
            type = "Mapper => ";
        } else if (className.contains("Service") == true) {
            type = "Service => ";
        }
        long end = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        logger.info(type + className + "=>" + methodName);
        logger.info("excute time : " + (end - start));
        return joinPoint.proceed();
    }

    // 특정 컨트롤러의 메소드가 수행될시 현재의 주소를 저장하는 용도
    @Around("execution(* com.example.controller.*Controller.*(..))")
    public Object sessionLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // 수행되는 클래스명
        String className = joinPoint.getSignature().getDeclaringTypeName();

        // 수행되는 메소드명
        String methodName = joinPoint.getSignature().getName();

        return joinPoint.proceed();
    }
}