package com.zhongyou.programing.synchronization.cyclicbarrier;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 计算该值在矩阵中出现的总次数</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-09 00:03</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Group implements Runnable {
    private Results results;

    public Group(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.format("Grouper starting...\n");
        int[] data = results.getData();
        for (int num : data) {
            finalResult += num;
        }
        System.out.format("Grouper ending..., finalResult=%d\n", finalResult);
    }
}
