package com.atguigu.threadsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @description <集合线程安全案例>
 * @author: <zhouchaoyu>
 * @Date: 2023/8/23 10:21
 *
 * Exception in thread "线程1" Exception in thread "线程0" Exception in thread "线程2"
 * Exception in thread "线程3" java.util.ConcurrentModificationException
 */
public class NotSafeDemo {
    /**
     * 多个线程同时对集合进行修改
     * @param args
     */
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }, "线程" + i).start();
        }
    }
}
