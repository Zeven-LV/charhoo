package com.charhoo.javaBase.thread.sync;

/**
 * 对method1和method2方法做了同步，确保了执行顺序
 * 原理：
 * 当方法调用时，调用指令将会检查方法的 ACC_SYNCHRONIZED 访问标志是否被设置，
 * 如果设置了，执行线程将先获取monitor，获取成功之后才能执行方法体，方法执行完后再释放monitor。
 * 在方法执行期间，其他任何线程都无法再获得同一个monitor对象。
 * 其实本质上没有区别，只是方法的同步是一种隐式的方式来实现，无需通过字节码来完成。
 */
public class SyncMethod {

    public synchronized void method1(){
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

    public synchronized void method2(){
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
        SyncMethod noSync = new SyncMethod();

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
