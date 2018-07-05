package com.charhoo.javaBase.thread.readwritemap;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class ReentrantReadWriteLock implements ReadWriteLock {
    @Override
    public Lock readLock() {
        return null;
    }

    @Override
    public Lock writeLock() {
        return null;
    }
}
