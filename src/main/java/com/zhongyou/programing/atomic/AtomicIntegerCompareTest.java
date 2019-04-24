package com.zhongyou.programing.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-11 10:52</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class AtomicIntegerCompareTest {

    private int value;

    public AtomicIntegerCompareTest(int value) {
        this.value = value;
    }

    public synchronized int increase() {
        return value++;
    }

    public static void main(String args[]) {
        long start = System.currentTimeMillis();

        AtomicIntegerCompareTest test = new AtomicIntegerCompareTest(0);
        for (int i = 0; i < 1000000; i++) {
            test.increase();
        }
        long end = System.currentTimeMillis();
        System.out.println("time elapse:" + (end - start));

        long start1 = System.currentTimeMillis();

        AtomicInteger atomic = new AtomicInteger(0);

        for (int i = 0; i < 1000000; i++) {
            atomic.incrementAndGet();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("time elapse:" + (end1 - start1));
    }
}
