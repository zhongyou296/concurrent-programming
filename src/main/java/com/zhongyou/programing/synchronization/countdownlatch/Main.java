package com.zhongyou.programing.synchronization.countdownlatch;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-08 21:07</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Main {

    public static void main(String[] args) {
        Videoconference videoconference = new Videoconference(10);
        Thread threadConference = new Thread(videoconference);
        threadConference.start();

        for (int i = 0; i < 10; ++i) {
            Thread thread = new Thread(new Participant(videoconference, "Participant:" + i));
            thread.start();
        }
    }
}
