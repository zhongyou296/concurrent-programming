package com.zhongyou.programing.synchronization.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 查找类</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-08 23:55</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Searcher implements Runnable {

    private int firstRow;
    private int lastRow;

    /**
     * 矩阵
     */
    private MatrixMock mock;
    /**
     * 用来保存查询的结果
     */
    private Results results;
    /**
     * 用来保存要查找的数字
     */
    private int number;
    /**
     * 用来等待多个线程执行完成后，再继续往下执行
     */
    private CyclicBarrier cyclicBarrier;

    public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier cyclicBarrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.mock = mock;
        this.results = results;
        this.number = number;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        int counter;
        System.out.format("ThreadName=%s, firstRow=%d, lastRow=%d\n", Thread.currentThread().getName(), firstRow, lastRow);
        for (int i = firstRow; i < lastRow; ++i) {
            counter = 0;
            int[] data = mock.getRows(i);
            for (int j = 0; j < data.length; ++j) {
                if (data[j] == number) {
                    ++counter;
                }
            }
            results.setData(firstRow, counter);
        }
        System.out.format("ThreadName=%s has finished\n", Thread.currentThread().getName());

        try {
            // 每一个线程到达集合点后就会调用await()方法通知cyclicBarrier对象，cyclicBarrier对象会让这个线程休眠直到其它线程都达到集合点，
            // 当所有线程都达到集合点后，cyclicBarrier对象就唤醒所有在await()方法里等待的线程，同时，还可以以构造器形式传入一个Runnable对象
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
