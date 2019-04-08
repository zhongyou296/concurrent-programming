package com.zhongyou.programing.synchronous;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-02 23:58</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Observer {

    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Thread producer = new Thread(new Producer(eventStorage));
        Thread consumer = new Thread(new Consumer(eventStorage));

        producer.start();
        consumer.start();
    }
}

class EventStorage {
    private int size;
    private LinkedList<Date> storage;

    public EventStorage() {
        this.size = 10;
        this.storage = new LinkedList<>();
    }

    public synchronized void set() {
        while (storage.size() == size) {
            try {
                // 当一个线程调用wait()方法时，JVM将这个线程置入休眠，其它线程可以竞争锁
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        // 唤醒阻塞在该锁上的线程
        notifyAll();
    }

    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                // 当一个线程调用wait()方法时，JVM将这个线程置入休眠，其它线程可以竞争锁
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Date date = storage.poll();
        System.out.println("value:" + date.toString());
        // 唤醒阻塞在该锁上的线程
        notifyAll();
    }
}

class Producer implements Runnable {

    private EventStorage eventStorage;

    public Producer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            eventStorage.set();
        }
    }
}

class Consumer implements Runnable {

    private EventStorage eventStorage;

    public Consumer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            eventStorage.get();
        }
    }
}
