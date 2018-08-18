package com.charhoo.javaBase.thread.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {
    public AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
        test.count.incrementAndGet();
        System.out.println(test.count);
    }

    public void test1(){


    }

}
