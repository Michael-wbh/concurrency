package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 饿汉模式
 * 单例实例在类装载时候进行创建
 * 使用饿汉模式需要考虑两个问题：1.私有构造函数在构造的时候没有太多处理。2.这个类在实际过程中肯定会被使用。这样不会带来资源的浪费。
 */
@Slf4j
@ThreadSafe
public class SingletonExample6 {

    // 私有构造函数
    private SingletonExample6() {
    }

    //单例对象
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    //静态的工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}