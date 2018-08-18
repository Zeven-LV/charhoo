package com.charhoo.javaBase.thread.sync;

/**
 * 虽然线程1和线程2都进入了对应的方法开始执行，但是线程2在进入同步块之前，需要等待线程1中同步块执行完成。
 *
 * 原理：每个对象有一个监视器锁（monitor）。当monitor被占用时就会处于锁定状态，线程执行monitorenter指令时尝试获取monitor的所有权
 */
public class SyncCode {

    public void method1(){
        System.out.println("method1 start!");
        try {
            synchronized (this){
                System.out.println("method1 execute!");
                Thread.sleep(1000);
            };
        } catch (InterruptedException e) {
            /**
             * https://www.ibm.com/developerworks/cn/java/j-jtp05236.html
             */
            e.printStackTrace();
        }
        System.out.println("method1 end!");
    }

    public void method2(){
        System.out.println("method2 start!");
        try {
            synchronized (this){
                System.out.println("method2 execute!");
                Thread.sleep(1000);
            };
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2 end!");
    }

    public static void main(String[] args) {
        final SyncCode noSync = new SyncCode();

        new Thread(new Runnable() {
            @Override
            public void run() {
                noSync.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                noSync.method2();
            }
        }).start();
    }
}
