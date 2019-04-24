package com.zhongyou.programing.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： </p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-23 23:57</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class ReportProcessor implements Runnable {

    private CompletionService<String> service;
    private boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        this.end = false;
    }

    @Override
    public void run() {
        while (!end) {
            try {
                Future<String> result = service.poll(20, TimeUnit.SECONDS);
                if (result != null) {
                    String report = result.get();
                    System.out.printf("ReportReceiver: Report Received:%s\n", report);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ReportSender: End\n");
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
