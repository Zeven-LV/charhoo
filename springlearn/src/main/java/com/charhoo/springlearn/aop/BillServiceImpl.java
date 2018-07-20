package com.charhoo.springlearn.aop;

public class BillServiceImpl implements BillService{
    @Override
    public void cost(int fee) {
        System.out.println("cost finish;");
    }

    @Override
    public void refund(int fee) {
        System.out.println("refund finish;");
    }
}
