package com.charhoo.javaBase.thread.sync;

import java.util.concurrent.atomic.AtomicInteger;

public class Test01 {

    private int value = 0;
    private AtomicInteger count = new AtomicInteger(0);

    public void add() {
        value++;
        count.incrementAndGet();
    }

    public static void main(String[] args) {
        final Test01 test01 = new Test01();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 1000 ; i++){
                    test01.add();
                    System.out.println("thread1:"+test01.value);
                    /*try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 1000 ; i++){
                    test01.add();
                    System.out.println("thread2:"+test01.value);
                    /*try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        }).start();
    }
}
