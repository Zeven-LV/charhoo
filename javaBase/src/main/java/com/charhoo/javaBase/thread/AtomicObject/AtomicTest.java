package com.charhoo.javaBase.thread.AtomicObject;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    public static void main(String[] args) {

    }

    public void intTest(){

    }

    public class IntegerTest{
        private Integer count = 0;
        /*使用synchronized关键字同步*/
        synchronized public void increment(){
            count++;
        }

    }

    /**
     * AtomicInteger提供原子操作
     * volatile修饰value 不存在内存可见性问题
     * final修饰方法
     * CAS算法：在加一之前先去看看value的值是多少，真正加的时候再去看一下，如果发现变了，不操作数据，否则为value加一。
     * 注意问题：
     * getAndIncrement与incrementAndGet区别，返回原值，返回增加后值
     */
    public void AtomicIntegerTest(){
        AtomicInteger i = new AtomicInteger();
        System.out.println(i.incrementAndGet());
        System.out.println(i.get());
        System.out.println(i.getAndIncrement());
        System.out.println(i.get());
    }
    /**
     * AtomicInteger Increment核心原理
     * public final int getAndAddInt(Object this, long offset, int increment) {
     *         int var5;
     *         do {
     *              //获取原值
     *             var5 = this.getIntVolatile(var1, var2);
     *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));//如果cas失败继续获取原值，直到成功
     *
     *         return var5;
     *     }
     */
}
