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
         * volatile修饰value 不存在内存可见性问题
         * final修饰方法
         * CAS算法：在加一之前先去看看value的值是多少，真正加的时候再去看一下，如果发现变了，不操作数据，否则为value加一。
         * */
        public void increment(){
            count.getAndIncrement();
        }

    }
}
