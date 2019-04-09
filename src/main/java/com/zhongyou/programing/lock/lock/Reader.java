package com.zhongyou.programing.lock.lock;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-07 22:35</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Reader implements Runnable {

    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.format("threadName:%s, price1=%f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
            System.out.format("threadName:%s, price2=%f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
        }
    }
}
