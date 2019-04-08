package com.zhongyou.programing.mythread;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 测试创建线程任务</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-03-31 20:08</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Calculator implements Runnable {

    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; ++i) {
            System.out.format("%s:%d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; ++i) {
            Calculator calculator = new Calculator(i);
            Thread thread = new Thread(calculator);
            thread.start();
        }
    }
}
