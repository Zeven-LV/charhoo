package com.charhoo.javaBase.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        String key = "pass";
        Integer keyHash = key.hashCode();
        System.out.println(Integer.toBinaryString(keyHash));
        System.out.println(Integer.toBinaryString(15));
        System.out.println(15&keyHash);

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
    }

    public void hashTableTest(){
        Hashtable hashtable = new Hashtable();
    }

    /**
     * hashMap及关键方法
     */
    public void hashMapTest(){
        HashMap hashMap = new HashMap();
        hashMap.put("1","1");
        hashMap.size();
        //hashMap.resize();

    }

    /**
     *  extends AbstractMap implements NavigableMap<K,V>, Cloneable
     */
    public void treeMapTest(){
        TreeMap treeMap = new TreeMap();
    }

    /**
     * 通过volatile关键字+synchronized代码块
     */
    public void concurrentHashMapTest(){
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.size();
    }

    /*
    final V putVal(int hashKey, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        //判断该hashmap是否为空
        if ((tab = table) == null || (n = tab.length) == 0)
            //如果为空，resize该hashMap ，长度n
            n = (tab = resize()).length;
        //index为（（length-1）& hashKey ）出的槽是否为空
        if ((p = tab[i = (n - 1) & hash]) == null)
            //为空则实例化该node放到index出
            tab[i] = newNode(hash, key, value, null);
        else {
            //不为空
            Node<K,V> e; K k;
            //如果（key值hashCode冲突），而且key值相同，则覆盖
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                //否则，如果p是TreeNode类型（该槽已经树化），将新值加入到树
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                //否则 p是链表类型
                for (int binCount = 0; ; ++binCount) {
                    //（条件一）链表的next节点为空
                    if ((e = p.next) == null) {
                        //把新址放到next节点
                        p.next = newNode(hash, key, value, null);
                        //判断是否达到树化阈值
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            //达到则树化
                            treeifyBin(tab, hash);
                        break;
                    }
                    //（条件二）如果next节点不为空，next节点与该值比较hashKey与key值
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        //如果相同则结束循环
                        break;
                    //如果条件而不成立，则
                    p = e;
                }
            }
            //如果e不为空
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                //？？？
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;

                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
    */

}
