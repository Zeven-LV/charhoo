package com.charhoo.javaBase.thread.threadlocal;

/**
 * 1，主线程先设置ThreadLocalTest01实例test的longLocal，stringLocal
 * 2，新线程再设置test的longLocal，stringLocal
 * 3，主线程获取test的longLocal，stringLocal还是1设置的值
 */
public class ThreadLocalTest01 {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();


    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest01 test = new ThreadLocalTest01();


        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());


        Thread thread1 = new Thread(){
            public void run() {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            };
        };
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
