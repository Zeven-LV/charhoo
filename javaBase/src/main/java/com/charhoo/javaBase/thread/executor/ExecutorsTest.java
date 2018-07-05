package com.charhoo.javaBase.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {

    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

    public static void main(String[] args) {
        int n_cpu = Runtime.getRuntime().availableProcessors();
        System.out.println(n_cpu);
    }
}
