package com.zhongyou.programing.mythread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-01 23:55</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class MyThreadFactory implements ThreadFactory {

    private String name;
    private int counter;
    private List<String> states;

    public MyThreadFactory(String name) {
        this.name = name;
        this.counter = 0;
        this.states = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, name + "-thread-" + counter);
        ++counter;
        states.add(thread.getId() + ":" + thread.getName());
        return thread;
    }

    public String getStats() {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : states) {
            stringBuffer.append(str + "\n");
        }
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        MyThreadFactory myThreadFactory = new MyThreadFactory("MyThreadFactory");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 10; ++i) {
            myThreadFactory.newThread(task);
        }
        System.out.println("MyThreadFactory Stats:" + myThreadFactory.getStats());
    }
}
