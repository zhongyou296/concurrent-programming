package com.zhongyou.programing.mythread;

import java.util.Date;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 验证线程局部变量</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-01 23:27</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class SafeTask implements Runnable {

    private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
        @Override
        protected Date initialValue() {
            return new Date();
        }
    };

    @Override
    public void run() {
        System.out.format("Thread started:%s : %s\n", Thread.currentThread().getId(), startDate.get());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.format("Thread ended:%s : %s\n", Thread.currentThread().getId(), startDate.get());
    }

    public static void main(String[] args) {
        SafeTask safeTask = new SafeTask();
        for (int i = 0; i < 10; ++i) {
            Thread thread = new Thread(safeTask);
            thread.start();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
