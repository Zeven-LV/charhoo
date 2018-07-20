package com.charhoo.springlearn.aop;

import java.lang.reflect.Proxy;

public class TestBillServiceByJDKProxy {

    public static void main(String[] args) {
        BillService billService = new BillServiceImpl();
        PerformanceHandler handler = new PerformanceHandler(billService);
        // 只能对接口进行代理
        BillService proxy = (BillService) Proxy.newProxyInstance(billService.getClass().getClassLoader(), billService.getClass().getInterfaces(), handler);
        proxy.cost(10);
        proxy.refund(1012);
        /**
         * begin monitor...
         * cost finish;
         * end monitor...
         * com.charhoo.springlearn.rmi.BillServiceImpl.cost cost 0ms.
         */
    }

}
