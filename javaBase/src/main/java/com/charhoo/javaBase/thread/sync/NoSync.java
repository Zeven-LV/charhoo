package com.charhoo.javaBase.thread.sync;

/**
 * https://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651481388&idx=1&sn=c6675e27cf042f48f26ee5c66ece9562&chksm=bd2509538a52804584d3a31004b63c65b271bbe7949312d993e41ef7c67808784c3f2cad9186&mpshare=1&scene=1&srcid=0701LA1qFD7CJ3TLk3KrMiAp&pass_ticket=wwsd0BRekcWvW8lGiMj0hSIAAHXEJJcvO9yYUjZd5qNXQVhZc%2B7o9WjFwUt6xT%2BT#rd
 * 在没有同步时，method1和method2谁先执行完是不确定的
 */
public class NoSync {

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
        NoSync noSync = new NoSync();

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
