package com.charhoo.javaBase.thread.executor;

import java.util.concurrent.*;

/**
 * Executor(excute())
 * -->ExecutorService(commit();shutdown();invoke())
 * -->AbstractExecutorService
 * -->ThreadPoolExecutor
 */
public class ExecutorsTest {

    /*创建单线程线程池*/
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    /*数量不固定线程池*/
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    /*创建固定数量线程池*/
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
    /*创建定时线程池*/
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

    public static void main(String[] args) {
        ExecutorsTest executorsTest = new ExecutorsTest();
        ExecutorService executorService = executorsTest.fixedThreadPool;
        for(int i=0;i<5;i++){
//            executorService.execute(new MyRunable());
            executorsTest.scheduledThreadPool.schedule(new MyRunable(),1, TimeUnit.SECONDS);
        }
//        executorService.shutdown();
        executorsTest.scheduledThreadPool.shutdown();
    }

}

class MyRunable implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}