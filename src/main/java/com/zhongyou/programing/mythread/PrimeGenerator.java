package com.zhongyou.programing.mythread;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-01 08:24</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class PrimeGenerator extends Thread {

    @Override
    public void run() {
        long number = 1L;
        while (true) {
            if (isPrime(number)) {
                System.out.format("Number %d is Prime\n", number);
            }
            if (isInterrupted()) {
                System.out.println("Be Interrupted\n");
                return;
            }
            ++number;
        }
    }

    private boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }
        for (long i = 2; i < number; ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Thread thread = new PrimeGenerator();
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 过上5秒，中断子线程
        thread.interrupt();
    }
}
