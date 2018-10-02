package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.NotThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class SingletonExample4 {

    // 私有构造函数
    private SingletonExample4() {
    }
    //正常情况下
    //1.memory = allocate() 分配对象的内存空间
    //2.ctorInstance() 初始化对象
    //3.instance = memory 设置instance指向刚分配的内存

    //JVM和cpu优化，发生了指令重排
    //1.memory = allocate() 分配对象的内存空间
    //3.instance = memory 设置instance指向刚分配的内存
    //2.ctorInstance() 初始化对象

    //单例对象
    private static SingletonExample4 instance = null;

    //静态的工厂方法
    public static  SingletonExample4 getInstance() {
        if (instance == null) {  //双重检查机制
            synchronized(SingletonExample4.class) { //同步锁
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}