package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.Recommend;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 枚举模式 ，最安全的单例模式
 */
@Slf4j
@ThreadSafe
@Recommend
public class SingletonExample7 {

    // 私有构造函数
    private SingletonExample7() {
    }

    //静态的工厂方法
    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingletonExample7();
    }

    private enum Singleton {

        INSTANCE;

        private SingletonExample7 singletonExample7;

        //jvm保证这个方法绝对只调用一次
        Singleton() {
            singletonExample7 = new SingletonExample7();
        }

        public SingletonExample7 getSingletonExample7() {
            return singletonExample7;
        }
    }
}