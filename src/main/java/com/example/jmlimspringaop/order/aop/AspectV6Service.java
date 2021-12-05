package com.example.jmlimspringaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Service {

    @Around("com.example.jmlimspringaop.order.aop.Pointcuts.allOrderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // @Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            // @AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            // 리턴값 조작 가능
            return result;
        } catch (Exception e) {
            // @AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            // @After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    @Before("com.example.jmlimspringaop.order.aop.Pointcuts.allOrderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.example.jmlimspringaop.order.aop.Pointcuts.allOrderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        // Around 와는 달리 return 값 조작은 안됨.
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "com.example.jmlimspringaop.order.aop.Pointcuts.allOrderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", joinPoint.getSignature(), ex);
    }

    @After("com.example.jmlimspringaop.order.aop.Pointcuts.allOrderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {} ", joinPoint.getSignature());
    }
}
