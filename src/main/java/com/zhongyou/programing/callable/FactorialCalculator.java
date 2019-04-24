package com.zhongyou.programing.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-21 13:51</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class FactorialCalculator implements Callable<Integer> {

    private int number;

    public FactorialCalculator(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if ((number == 0) || (number == 1)) {
            result = 1;
        } else {
            for (int i = 2; i <= number; ++i) {
                result *= 1;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        return result;
    }
}
