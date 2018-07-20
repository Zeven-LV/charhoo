package com.charhoo.javaBase.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        reentrantLockTest.lockTest();
    }

    public void lockTest(){
        Count count = new Count();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j<1000;j++ )
                        System.out.println(Thread.currentThread().getName()+":"+count.increat());
                }
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
        System.out.println("-----------"+count.getI());
    }
}
