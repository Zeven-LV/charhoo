package com.charhoo.javaBase.thread.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1、创建的线程池具体配置为：核心线程数量为5个；全部线程数量为10个；工作队列的长度为5。
 * 2、我们通过queue.size()的方法来获取工作队列中的任务数。
 * 3、运行原理：
 *       刚开始都是在创建新的线程，达到核心线程数量5个后，新的任务进来后不再创建新的线程，
 *       而是将任务加入工作队列，任务队列到达上线5个后，新的任务又会创建新的普通线程，
 *       直到达到线程池最大的线程数量10个，后面的任务则根据配置的饱和策略来处理。
 *       我们这里没有具体配置，使用的是默认的配置AbortPolicy:直接抛出异常。
 * 　　当然，为了达到我需要的效果，上述线程处理的任务都是利用休眠导致线程没有释放！！！
 *
 * 饱和状态：线程池和队列都满了
 * 饱和策略：
 * 1、AbortPolicy：直接抛出异常
 * 2、CallerRunsPolicy：只用调用所在的线程运行任务
 * 3、DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
 * 4、DiscardPolicy：不处理，丢弃掉。
 *
 * 根据运行结果：当corePoolSize,慢且队列满时创建新的线程，直到线程数达到maximumPoolSize；
 */
public class ThreadPoolTest implements Runnable{
    @Override
    public void run() {
        try{
            Thread.sleep(300);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(5);
        //饱和策略
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();//.DiscardPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,30,TimeUnit.SECONDS,queue,handler);

        try {
            for (int i = 0; i < 16; i++) {
                executor.execute(new Thread(new ThreadPoolTest(), "Thread".concat(i + "")));
                System.out.println("线程池中活跃线程数：" + executor.getPoolSize());
                if (queue.size() > 0) {
                    System.out.println("队列中阻塞队列数：" + queue.size());
                }
            }
        }finally {
            executor.shutdown();
        }

    }
}
