package com.zhongyou.programing.lock;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-07 22:42</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Main {

    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();

        Reader[] readers = new Reader[5];
        Thread[] threadReaders = new Thread[5];
        for (int i = 0; i < 5; ++i) {
            readers[i] = new Reader(pricesInfo);
            threadReaders[i] = new Thread(readers[i]);
        }

        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);

        for (int i = 0; i < 5; ++i) {
            threadReaders[i].start();
        }
        threadWriter.start();
    }
}
