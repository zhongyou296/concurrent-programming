package com.zhongyou.programing.synchronization.semaphore;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-08 00:07</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Job implements Runnable {

    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("ThreadName=%s start print\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("ThreadName=%s end print\n", Thread.currentThread().getName());
    }
}
