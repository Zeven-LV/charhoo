package com.charhoo.javaBase.thread.PrngTest;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicPseudoRandom {

    private AtomicInteger seed;

    AtomicPseudoRandom(int seed){
        this.seed = new AtomicInteger(seed);
    }

    public int nextInt(int n){
        while (true){
            int s = seed.get();
            int nextSeed = deal(s);
            if(seed.compareAndSet(s, nextSeed)){
                int remainder = s % n;
                return remainder > 0?remainder:remainder+n;
            }
        }
    }

    public int deal(int s){
        return s;
    }

}
