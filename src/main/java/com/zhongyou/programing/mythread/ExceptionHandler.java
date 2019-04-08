package com.zhongyou.programing.mythread;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 包装运行时异常</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-01 23:17</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Caught a Exception");
        System.out.println("ThreadId:%s" + t.getId());
    }
}
