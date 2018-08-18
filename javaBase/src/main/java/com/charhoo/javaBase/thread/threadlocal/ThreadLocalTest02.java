package com.charhoo.javaBase.thread.threadlocal;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程个数为3的线程池运行6个线程，3个线程在第一次获取format时format为空，第二次时format存在
 */
public class ThreadLocalTest02 {

    private static ThreadLocal dateFormat = new ThreadLocal();

    private SimpleDateFormat getFormat(){

        SimpleDateFormat format = (SimpleDateFormat)dateFormat.get();
        if(format == null){
            System.out.println(Thread.currentThread().getName()+":"+null);
            format = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.set(format);
        }else {
            System.out.println(Thread.currentThread().getName()+":exist");
        }
        return format;
    }

    public static void main(String[] args) {
        ThreadLocalTest02 test02 = new ThreadLocalTest02();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=0;i<6;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+":"+test02.getFormat());
                }
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}
