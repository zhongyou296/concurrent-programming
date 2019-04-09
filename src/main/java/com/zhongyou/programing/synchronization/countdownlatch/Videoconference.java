package com.zhongyou.programing.synchronization.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 视频会议系统</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-08 21:01</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Videoconference implements Runnable {

    /**
     * 等待countDownLatch为0时，则开始开会
     */
    private CountDownLatch countDownLatch;

    public Videoconference(int num) {
        this.countDownLatch = new CountDownLatch(num);
    }

    public synchronized void arrive(String name) {
        System.out.format("%s has arrived\n", name);
        countDownLatch.countDown();
        System.out.format("Waiting for %d participants\n", countDownLatch.getCount());
    }

    @Override
    public void run() {
        System.out.format("count=%d\n", countDownLatch.getCount());
        try {
            countDownLatch.await();
            System.out.format("All the participants has arrived");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
