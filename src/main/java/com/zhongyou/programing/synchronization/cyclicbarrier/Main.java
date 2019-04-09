package com.zhongyou.programing.synchronization.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-08 23:46</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Main {
    /**
     * 行数
     */
    public static final int ROWS = 10000;
    /**
     * 列数
     */
    public static final int NUMBERS = 1000;
    /**
     * 要查找的数字
     */
    public static final int SEARCH = 5;
    /**
     * 参与线程个数
     */
    public static final int PARTICIPANTS = 5;
    public static final int LINES_PARTICIPANT = 2000;

    public static void main(String[] args) {
        MatrixMock matrixMock = new MatrixMock(ROWS, NUMBERS, SEARCH);
        Results results = new Results(ROWS);
        Group group = new Group(results);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTICIPANTS, group);
        Searcher[] searchers = new Searcher[PARTICIPANTS];
        Thread[] threads = new Thread[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; ++i) {
            searchers[i] = new Searcher(
                    i * LINES_PARTICIPANT,
                    (i * LINES_PARTICIPANT) + LINES_PARTICIPANT,
                    matrixMock,
                    results,
                    5,
                    cyclicBarrier);
            threads[i] = new Thread(searchers[i]);
        }

        for (int i = 0; i < PARTICIPANTS; ++i) {
            threads[i].start();
        }

        for (int i = 0; i < PARTICIPANTS; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Main Thread has finished...");
    }
}
