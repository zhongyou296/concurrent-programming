package com.zhongyou.programing.mythread;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： Java提供的一种在线程对象中捕获和处理运行时异常的机制</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-01 23:18</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class CaughtExceptionInThread implements Runnable {

    @Override
    public void run() {
        Integer result = Integer.parseInt("haha");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new CaughtExceptionInThread());
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}
