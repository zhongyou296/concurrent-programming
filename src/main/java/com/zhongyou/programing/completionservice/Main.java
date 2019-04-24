package com.zhongyou.programing.completionservice;

import java.util.concurrent.*;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-24 00:01</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
        CompletionService<String> service = new ExecutorCompletionService<>(executor);

        ReportRequest reportRequest = new ReportRequest("Face", service);
        ReportRequest onlineRequest = new ReportRequest("Online", service);
        Thread faceThread = new Thread(reportRequest);
        Thread onlineThread = new Thread(onlineRequest);

        ReportProcessor reportProcessor = new ReportProcessor(service);
        Thread sendThread = new Thread(reportProcessor);

        System.out.printf("Main: Starting the threads\n");
        faceThread.start();
        onlineThread.start();
        sendThread.start();


        try {
            System.out.printf("Main: Waiting for the report generators\n");
            faceThread.join();
            onlineThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Shutting down the executor\n");
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reportProcessor.setEnd(true);
        System.out.println("Main: Ends");
    }
}
