package com.zhongyou.programing.semaphore;

import java.util.concurrent.Semaphore;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 二进制信号量</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-08 00:04</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class PrintQueue {

    private Semaphore semaphore;

    public PrintQueue() {
        this.semaphore = new Semaphore(1);
    }

    public void printJob(Object document) {
        try {
            semaphore.acquire();
            long duration = (long) Math.random() * 10;
            System.out.printf("threadName=%s, duration=%d\n", Thread.currentThread().getName(), duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
