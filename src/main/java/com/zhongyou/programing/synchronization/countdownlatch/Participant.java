package com.zhongyou.programing.synchronization.countdownlatch;

import java.util.concurrent.TimeUnit;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-08 21:05</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Participant implements Runnable {

    private Videoconference videoconference;
    private String name;

    public Participant(Videoconference videoconference, String name) {
        this.videoconference = videoconference;
        this.name = name;
    }

    @Override
    public void run() {
        long duration = (long) Math.random() * 10;
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        videoconference.arrive(name);
    }
}
