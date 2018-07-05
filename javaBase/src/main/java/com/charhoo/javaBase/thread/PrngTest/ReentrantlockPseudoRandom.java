package com.charhoo.javaBase.thread.PrngTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantlockPseudoRandom {

    private final Lock lock = new ReentrantLock(false);
    private int seed;

    ReentrantlockPseudoRandom(int seed){
        this.seed = seed;
    }

    public int nextInt(int n){
        lock.lock();
        try{
            int s = seed;
            seed = deal(s);
            int remainder = s % n;
            return remainder > 0?remainder:remainder+n;
        }finally {
            lock.unlock();
        }
    }

    public int deal(int s){
        return s;
    }


}
