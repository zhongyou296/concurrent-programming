package com.zhongyou.programing.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 服务端</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-17 09:48</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Server {

    private ThreadPoolExecutor threadPoolExecutor;

    public Server() {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        System.out.println("poolSize: " + threadPoolExecutor.getPoolSize());
    }

    public void executeTask(Task task) {
        System.out.printf("Server: A new task has arrived\n");
        threadPoolExecutor.execute(task);
        System.out.printf("Server: Pool size: %d\n", threadPoolExecutor.getPoolSize());
        System.out.printf("Server: Active count: %d\n", threadPoolExecutor.getActiveCount());
        System.out.printf("Server: Completed tasks: %d\n", threadPoolExecutor.getCompletedTaskCount());
    }

    public void endServer() {
        threadPoolExecutor.shutdown();
    }
}
