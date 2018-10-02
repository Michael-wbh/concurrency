package com.mmall.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 注：SynchronizedExample1如果是一个父类，其子类如果要调用test2方法，是不会带上synchronized关键字的。
 * 原因：synchronized不属于方法的一部分，子类需要想要test2用也加锁，必须手动添加synchronized关键字。
 */
@Slf4j
public class SynchronizedExample1 {



    //修饰一个代码块
     public void test1(int j) {
         synchronized (this) {
             for (int i = 0; i < 10; i++) {
                 log.info("test1 - {} - {}",j ,i);
             }
         }
     }

    //修饰一个方法
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} - {}",j ,i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test2(1);
        });
        executorService.execute(() -> {
            synchronizedExample2.test2(2);
        });
    }
}