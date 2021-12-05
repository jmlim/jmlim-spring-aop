package com.example.jmlimspringaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Order {

    @Aspect
    @Order(1)
    public static class LogAspect {
        @Around("com.example.jmlimspringaop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(2)
    public static class TransactionAspect {
        // com.example.jmlimspringaop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
        /**
         * @Around("allOrder() && allService()")
         * 포인트컷은 이렇게 조합할 수 있다. && (AND), || (OR), ! (NOT) 3가지 조합이 가능하다.
         */
        @Around("com.example.jmlimspringaop.order.aop.Pointcuts.allOrderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
            }
        }
    }
}
