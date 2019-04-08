package com.zhongyou.programing.mythread;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 测试线程优先级</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-03-31 20:35</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class CalculatorByPriority implements Runnable {

    int number;

    public CalculatorByPriority(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000; ++i) {
            System.out.format("%s:%d * %d = %d\n",
                    Thread.currentThread().getName(),
                    number,
                    i,
                    i * number);
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];

        // 调节线程优先级
        for (int i = 0; i < 10; ++i) {
            threads[i] = new Thread(new CalculatorByPriority(i));
            // 将部分线程优先级调至最大
            if ((i % 2) == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("thread-" + i);
        }

        // 初始化线程状态数组
        for (int i = 0; i < 10; ++i) {
            System.out.println("state:" + threads[i].getState());
            states[i] = threads[i].getState();
        }

        for (int i = 0; i < 10; ++i) {
            threads[i].start();
        }

        boolean finish = false;
        while (!finish) {
            for (int i = 0; i < 10; ++i) {
                if (threads[i].getState() != states[i]) {
                    System.out.println("我状态变了, threadName:" + threads[i].getName() + ", priority:" + threads[i].getPriority());
                    states[i] = threads[i].getState();
                }
            }
            finish = true;
            for (int i = 0; i < 10; ++i) {
                finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
            }
        }
    }
}
