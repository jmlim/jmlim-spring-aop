package com.example.jmlimspringaop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.example.jmlimspringaop.order..*(..))")
    public void allOrder() {
    } // pointcut signature

    // 클래스 이름 패턴이 *Service 인거
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    // allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void allOrderAndService() {
    }
}
