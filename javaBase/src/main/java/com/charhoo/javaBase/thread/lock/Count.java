package com.charhoo.javaBase.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Count {

    private int i = 0;

    private final ReentrantLock lock = new ReentrantLock();

    public int increat(){
        lock.lock();
        try{
            i+=1;
        }finally {
            lock.unlock();
        }
        return i;
    }

    public int getI(){
        return i;
    }


}
