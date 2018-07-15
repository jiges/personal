package com.ccr;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 弱引用关联对象何时被回收
 * Created by ccr at 2018/7/14.
 */
public class WeakReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        //100M的缓存数据
        byte[] cacheData = new byte[100 * 1024 * 1024];
        //将缓存数据用软引用持有
        WeakReference<byte[]> cacheRef = new WeakReference<>(cacheData);
        System.out.println("第一次GC前" + cacheData);
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + cacheData);
        System.out.println("第一次GC后" + cacheRef.get());

        //将缓存数据的强引用去除
        cacheData = null;
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第二次GC后" + cacheData);
        System.out.println("第二次GC后" + cacheRef.get());

        //某个类中有这样一段代码
        Object key = new Object();
        Object value = new Object();
        putToContainer(key,value);

        //..........
        /**
         * 若干调用层次后程序员发现这个key指向的对象没有用了，
         * 为了节省内存打算把这个对象抛弃，然而下面这个方式真的能把对象回收掉吗？
         * 由于container对象中包含了这个对象的引用,所以这个对象不能按照程序员的意向进行回收.
         * 并且由于在程序中的任何部分没有再出现这个键，所以，这个键 / 值 对无法从映射中删除。
         * 很可能会造成内存泄漏。
         */
        key = null;
    }
    static Map<Object,Object> container = new HashMap<>();
    public static void putToContainer(Object key,Object value){
        container.put(key,value);
    }
}
