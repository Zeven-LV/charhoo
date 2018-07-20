package com.charhoo.springlearn.aop;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 前置增强
 */
public class PerformanceBeforeHandler implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        String value = String.valueOf(objects[0]);
        System.out.println(method + ":" + value);
    }

    public static void main(String[] args) {
        BillService billService = new BillServiceImpl();
        BeforeAdvice beforeAdvice = new PerformanceBeforeHandler();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(billService);
        proxyFactory.addAdvice(beforeAdvice);
        BillService billServiceProxy = (BillService) proxyFactory.getProxy();
        billServiceProxy.cost(10);
        billServiceProxy.refund(100);

    }
}
