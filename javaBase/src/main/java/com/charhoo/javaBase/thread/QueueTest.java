package com.charhoo.javaBase.thread;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueTest {

    /*定长缓存等待队列*/
    volatile ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(100);

    public static void main(String[] args) {

        /*无缓冲等待队列*/
//        SynchronousQueue synchronousQueue = new SynchronousQueue();

        /*无界缓存等待队列*/
//        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

        new Thread(new QueueTest().new MyRunable("put")).start();
        new Thread(new QueueTest().new MyRunable("take")).start();




    }

    class MyRunable implements Runnable{

        private String action = null;

        MyRunable(String action){
            this.action = action;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":"+action);
            if("put".equals(action)){
                try {
                    arrayBlockingQueue.put("hello world");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if("take".equals(action)){
                try {
                    String result = String.valueOf(arrayBlockingQueue.take());
                    System.out.println("take:"+result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" end");

        }
    }

}


