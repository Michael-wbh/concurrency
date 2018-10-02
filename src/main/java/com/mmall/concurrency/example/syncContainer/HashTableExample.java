package com.mmall.concurrency.example.syncContainer;

import com.mmall.concurrency.annoations.NotThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class HashTableExample {

    //请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static Hashtable<Integer, Integer> map = new Hashtable<>();

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool(); //线程池
        final Semaphore semaphore = new Semaphore(threadTotal); //信号量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); //计数器
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("==Exception==", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", map.size());
    }

    private static void update(int i) {
        map.put(i, i);
    }
}