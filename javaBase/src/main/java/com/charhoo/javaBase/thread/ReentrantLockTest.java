package com.charhoo.javaBase.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 显式锁用法
 */
public class ReentrantLockTest {

    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {



    }

    public void test(){
        lock.lock();
        try{

        }finally {
            lock.unlock();
        }

        //可定时
        try{
            lock.tryLock(10,TimeUnit.DAYS);
            try{

            }finally {
                lock.unlock();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //可中断
        try{
            lock.lockInterruptibly();
            try{

            }finally {
                lock.unlock();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //多队列
    }


}
