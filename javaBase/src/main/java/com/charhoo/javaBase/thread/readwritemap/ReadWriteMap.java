package com.charhoo.javaBase.thread.readwritemap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * 读写锁
 * @param <K>
 * @param <V>
 */
public class ReadWriteMap<K, V>  {

    private Map<K,V> map;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock rLock = lock.readLock();
    private final Lock wLock = lock.writeLock();

    ReadWriteMap(Map<K,V> map){
        this.map = map;
    }

    public V put(K key, V value){
        wLock.lock();
        try{
return map.put(key,value);
        }finally {
            wLock.unlock();
        }
    }

    public V get(Object key){
        rLock.lock();
        try{
            return map.get(key);
        }finally {
            rLock.unlock();
        }
    }

    public static void main(String[] args) {
        Map<String, Object> map = new ConcurrentHashMap();
    }

}
