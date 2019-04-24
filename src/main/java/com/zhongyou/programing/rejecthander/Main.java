package com.zhongyou.programing.rejecthander;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-24 00:23</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Main {

    public static void main(String[] args) {
        RejectTaskController rejectTaskController = new RejectTaskController();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.setRejectedExecutionHandler(rejectTaskController);

        for (int i = 0; i < 10; ++i) {
            Task task = new Task("task: " + i);
            executor.submit(task);
        }

        System.out.printf("Main: Shutting down the Executor.\n");
        executor.shutdown();
        Task rejectedTask = new Task("reject");
        executor.submit(rejectedTask);
        System.out.println("Main: End\n");

    }
}
