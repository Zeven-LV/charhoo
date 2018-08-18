package com.charhoo.javaBase.thread.sync;

/**
 * 对静态方法的同步本质上是对类的同步（静态方法本质上是属于类的方法，而不是对象上的方法），
 * 所以即使test和test2属于不同的对象，但是它们都属于SynchronizedTest类的实例，
 * 所以也只能顺序的执行method1和method2，不能并发执行。
 */
public class SyncStaticMethod {

    public static synchronized void method1(){
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

    public static synchronized void method2(){
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

        final SyncStaticMethod syncStaticMethod1 = new SyncStaticMethod();
        final SyncStaticMethod syncStaticMethod2 = new SyncStaticMethod();
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncStaticMethod1.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                syncStaticMethod2.method2();
            }
        }).start();
    }
}
