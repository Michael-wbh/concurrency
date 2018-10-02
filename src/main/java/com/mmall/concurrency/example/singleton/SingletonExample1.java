package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 线程不安全发生在24-26行，应为一个当一个线程执行到25行，new一个对象后，其他线程可能执行到24行判断空，会再次new对象。这样就会导致不是单例.
 */
@Slf4j
@NotThreadSafe
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1() {
    }

    //单例对象
    private static SingletonExample1 instance = null;

    //静态的工厂方法
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}