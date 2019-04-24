package com.zhongyou.programing.executor;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-17 09:57</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        for (int i = 0; i < 10; ++i) {
            server.executeTask(new Task("Task:" + i));
        }
        server.endServer();
    }
}
