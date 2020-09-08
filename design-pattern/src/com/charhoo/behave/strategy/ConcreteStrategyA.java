package com.charhoo.behave.strategy;

/**
 * @Description:
 * @Auther: lvzf
 * @Date: 2020/9/1
 */
public class ConcreteStrategyA extends Strategy {
    @Override
    public void algorithmInterface() {
        System.out.println("策略A");
    }
}
