package com.charhoo.javaBase.thread.executor;

import java.util.concurrent.*;

/**
 * Executor(excute())
 * -->ExecutorService(commit();shutdown();invoke())
 * -->AbstractExecutorService
 * -->ThreadPoolExecutor
 *
 * 实际开发中避免使用Executors创建线程池
 * 1）FixedThreadPool 和 SingleThreadPool:   允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
 * 2）CachedThreadPool 和 ScheduledThreadPool:   允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。
 */
public class ExecutorsTest {

    /**
     * 创建单线程线程池
     * threadPool(1,1,0,mis,LinkedBlockingQueue)
     * 线程池内只有一个线程
     * */
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(); //
    /**
     * 数量不固定线程池
     * threadPool(0,max,60,secend,SynchronousQueue)
     * 提交线程池几个任务就有几个线程
     * */
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    /**
     * 创建固定数量线程池
     * threadPool(n,n,0,mis,LinkedBlockingQueue)
     * 线程池中有指定数量的线程
     * */
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
    /**
     * 创建定时线程池
     * threadPool(n,max,0,non,DelayedWorkQueue)
     *指定数量的线程
     * */
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

    public static void main(String[] args) {

        ExecutorsTest executorsTest = new ExecutorsTest();
        ExecutorService executorService = executorsTest.scheduledThreadPool;
        for(int i=0;i<5;i++){
            executorService.execute(new MyRunable());
        }
        executorService.shutdown();
    }

}

class MyRunable implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}