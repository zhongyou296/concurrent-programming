package com.zhongyou.programing.executor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 任务结构</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-16 09:40</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Task implements Runnable {

    private Date initDate;
    private String name;

    public Task(String name) {
        initDate = new Date();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Task %s: Created on:%s\n", Thread.currentThread().getName(), name, initDate);
        System.out.printf("%s: Task %s: Started on:%s\n", Thread.currentThread().getName(), name, new Date());

        try {
            Long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Task %s:Doing a task during %d seconds\n", Thread.currentThread().getName(), name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: Task %s: Finished on:%s\n", Thread.currentThread().getName(), name, new Date());
    }
}
