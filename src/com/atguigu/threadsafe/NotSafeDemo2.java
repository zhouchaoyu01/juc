package com.atguigu.threadsafe;

import java.util.*;

/**
 *  Collections.synchronizedList
 * @description <集合线程安全案例>
 * @author: <zhouchaoyu>
 * @Date: 2023/8/23 10:21
 *
 */
public class NotSafeDemo2 {
    /**
     * 多个线程同时对集合进行修改
     * @param args
     */
    public static void main(String[] args) {
        List list = Collections.synchronizedList(new ArrayList());
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }, "线程" + i).start();
        }
    }
}
