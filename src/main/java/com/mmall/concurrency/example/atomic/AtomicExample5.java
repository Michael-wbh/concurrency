package com.mmall.concurrency.example.atomic;



import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample5 {
    //AtomicIntegerFieldUpdater 可以原子性地更新某一个类中的某一个字段，这个字段必须要被特殊关键字修饰才可以。
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");
    //这个字段count必须要被特殊关键字修饰才可以。(volatile作者非static)
    @Getter
    public volatile int count = 100;

    public static void main(String[] args) throws Exception{
        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5, 12100, 0)) {
            log.info("update success 1, {}", example5.getCount());
        }
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 2, {}", example5.getCount());
        } else {
            log.info("update failed, {}", example5.getCount());
        }
    }
}