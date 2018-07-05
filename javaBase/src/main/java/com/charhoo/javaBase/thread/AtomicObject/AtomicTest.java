package com.charhoo.javaBase.thread.AtomicObject;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger();
        i.set(1);
        System.out.println(i.get());
    }

    public void intTest(){

    }

    public class IntegerTest{
        private Integer count = 0;
        /*使用synchronized关键字同步*/
        synchronized public void increment(){
            count++;
        }

    }

    public class AtomicIntegerTest{
        private AtomicInteger count = new AtomicInteger(0);
        /**
         * AtomicInteger提供原子操作
         * volatile修饰值
         * final修饰方法
         * */
        public void increment(){
            count.getAndIncrement();
        }

    }
}
