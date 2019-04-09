package com.zhongyou.programing.synchronization.semaphore;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 同一时刻，只能有一个线程访问PrintQueue临界区</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-08 00:09</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Main {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; ++i) {
            threads[i] = new Thread(new Job(printQueue), "thread-" + i);
        }

        for (int i = 0; i < 10; ++i) {
            threads[i].start();
        }
    }
}
