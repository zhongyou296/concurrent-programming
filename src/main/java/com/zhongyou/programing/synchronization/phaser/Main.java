package com.zhongyou.programing.synchronization.phaser;

import java.util.concurrent.Phaser;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： Phaser同步机制用于控制多任务多阶段并发执行</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-09 23:42</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Main {

    public static void main(String[] args) {
        // 同步三个线程
        Phaser phaser = new Phaser(3);
        FileSearch fileSearch1 = new FileSearch("/Users/wangqiming/Desktop/comet", ".doc", phaser);
        FileSearch fileSearch2 = new FileSearch("/Users/wangqiming/Desktop/bugatti", "aa", phaser);
        FileSearch fileSearch3 = new FileSearch("/Users/wangqiming/Desktop/appolo", ".doc", phaser);

        Thread thread1 = new Thread(fileSearch1, "fileSearch1");
        thread1.start();
        Thread thread2 = new Thread(fileSearch2, "fileSearch2");
        thread2.start();
        Thread thread3 = new Thread(fileSearch3, "fileSearch3");
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(phaser.isTerminated());
    }
}
