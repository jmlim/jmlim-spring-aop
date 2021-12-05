package com.example.jmlimspringaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect // 위 어노테이션 만으로는 bean 에 등록되지 않음.
public class AspectV1 {
    @Around("execution(* com.example.jmlimspringaop.order..*(..))")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // joinPoint 시그니쳐
        return joinPoint.proceed(); // 실제 타겟 호출
    }
}
