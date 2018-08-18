package com.charhoo.javaBase.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {

    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(obj);
        //软引用,非必须引用，内存溢出之前进行回收
//        SoftReference softReference = new SoftReference(obj);
        //弱引用,第二次垃圾回收时回收
//        WeakReference weakReference = new WeakReference(obj);
//        System.out.println(weakReference.isEnqueued());//返回是否从内存中已经删除
//        System.out.println(weakReference);


        //虚引用,垃圾回收时回收
        PhantomReference phantomReference = new PhantomReference(obj,new ReferenceQueue());
        obj = null;
        System.out.println(phantomReference.get());//永远返回null
        System.out.println(phantomReference.isEnqueued());//返回是否从内存中已经删除



    }
}
