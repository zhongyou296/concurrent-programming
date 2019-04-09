package com.zhongyou.programing.synchronization.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-10 00:09</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Consumer implements Runnable {

    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; ++i) {
            System.out.printf("Consumer: Cycle %d\n", cycle);

            try {
                // 与生产者进行交换数据存储结构，调用exchange()方法后，该线程将阻塞，直至其它线程到达
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Consumer: " + buffer.size());
            for (int j = 0; j < buffer.size(); ++j) {
                String message = buffer.get(j);
                System.out.format("Consumer message:%s\n", message);
                buffer.remove(j);
            }
            ++cycle;
        }
    }
}
