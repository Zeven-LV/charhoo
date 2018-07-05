package com.charhoo.javaBase.thread.sync;

/**
 * 在没有同步时，method1和method2谁先执行完是不确定的
 */
public class SyncProgram {

    public void method1(){
        System.out.println("method1 start!");
        try {
            System.out.println("method1 execute!");
            Thread.sleep(1000);
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
            System.out.println("method2 execute!");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2 end!");
    }

    public static void main(String[] args) {
        SyncProgram noSync = new SyncProgram();

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
