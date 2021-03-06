package com.example.jmlimspringaop;

import com.example.jmlimspringaop.order.OrderRepository;
import com.example.jmlimspringaop.order.OrderService;
import com.example.jmlimspringaop.order.aop.AspectV4Pointcut;
import com.example.jmlimspringaop.order.aop.AspectV5Order;
import com.example.jmlimspringaop.order.aop.AspectV6Service;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(AspectV6Service.class)
// @Import({AspectV5Order.LogAspect.class, AspectV5Order.TransactionAspect.class})
// @Import(AspectV4Pointcut.class)
// @Import(AspectV3.class)
// @Import(AspectV2.class)
//@Import(AspectV1.class)
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        Assertions.assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }
}
