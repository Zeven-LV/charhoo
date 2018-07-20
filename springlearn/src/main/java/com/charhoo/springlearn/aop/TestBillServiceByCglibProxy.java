package com.charhoo.springlearn.aop;

public class TestBillServiceByCglibProxy {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        CglibProxy proxy = new CglibProxy();
        // 可以对实现类进行代理
        BillService billService = (BillServiceImpl) proxy.getProxy(BillServiceImpl.class);
        billService.cost(10);
        billService.refund(100);
    }
}
