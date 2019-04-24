package com.zhongyou.programing.rejecthander;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 如何处理被执行器拒绝的任务</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-24 00:18</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class RejectTaskController implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("RejectTaskController: The task %s has been rejected\n", r.toString());
        System.out.printf("RejectTaskController: %s\n", executor.toString());

        System.out.printf("RejectTaskController: Terminating: %s\n", executor.isTerminating());
        System.out.printf("RejectTaskController: Terminated: %s\n", executor.isTerminated());
    }
}
