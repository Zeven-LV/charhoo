package com.charhoo.javaBase.thread.executor;

import java.util.concurrent.*;

/**
 * 线程饥饿死锁
 */
public class ThreadDeadlock {
    ExecutorService executors = Executors.newSingleThreadExecutor();

    public  class RenderPageTask implements Callable<String>{

//        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = executors.submit(new LoadFileTask("header.html"));
            footer = executors.submit(new LoadFileTask("footer.html"));
            String page = "body";//renderBody();
            //出现死锁-任务等待子任务的结果
            return header.get() + page + footer.get();
        }
    }

    public  class LoadFileTask implements Callable<String>{

        LoadFileTask(String page){
        }
//        @Override
        public String call() throws Exception {
            return "page";
        }
    }
}
