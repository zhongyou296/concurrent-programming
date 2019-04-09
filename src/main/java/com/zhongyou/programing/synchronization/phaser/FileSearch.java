package com.zhongyou.programing.synchronization.phaser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 多任务多阶段查找符合条件的文件</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-09 23:19</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class FileSearch implements Runnable {

    /**
     * 用来存储查找的文件夹
     */
    private String initPath;

    /**
     * 用来存储要查找的文件的扩展名
     */
    private String end;

    /**
     * 用来存储查找的文件的完整路径
     */
    private List<String> results;

    /**
     * 控制任务不同阶段的同步
     */
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.results = new ArrayList<>();
        this.phaser = phaser;
    }

    /**
     * 用来处理文件夹
     *
     * @param file
     */
    private void directoryProcess(File file) {
        File[] files = file.listFiles();
        if (null != files && files.length != 0) {
            for (int i = 0; i < files.length; ++i) {
                if (files[i].isFile()) {
                    fileProcess(files[i]);
                } else {
                    directoryProcess(files[i]);
                }
            }
        }
    }

    private void fileProcess(File file) {
        if (file.getName().endsWith(end)) {
            results.add(file.getAbsolutePath());
        }
    }

    /**
     * 过滤出过去24小时内创建的文件
     */
    private void filterResultsIn24Hour() {
        List<String> newResults = new ArrayList<>();
        long actualDate = System.currentTimeMillis();

        for (String filePath : results) {
            File file = new File(filePath);
            if (actualDate - file.lastModified() < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                newResults.add(filePath);
            }
        }
        results = newResults;
    }

    /**
     * 检查结果集是否为空
     */
    private boolean checkResultsIsEmpty() {
        // 若结果集为空，则不进行下一阶段运算
        if (results.isEmpty()) {
            System.out.format("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
            phaser.arriveAndDeregister();
            return false;
        } else {
            // 通知Phaser对象当前线程已经完成了该节点，阻塞等待其他线程该阶段执行完成
            System.out.format("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), phaser.getPhase(),
                    results.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }

    }

    /**
     * 将结果集打印到控制台
     */
    private void showInfo() {
        for (String filePath : results) {
            File file = new File(filePath);
            System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsoluteFile());
        }
        phaser.arriveAndAwaitAdvance();
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Starting.\n", Thread.currentThread().getName());

        // step1:查找
        File file = new File(initPath);
        if (file.isDirectory()) {
            directoryProcess(file);
        } else {
            fileProcess(file);
        }

        // step2:过滤非24小时内的
        filterResultsIn24Hour();

        // step3:校验结果集非空
        if (!checkResultsIsEmpty()) {
            return;
        }

        // step4:打印结果集到控制台
        showInfo();

        // step5:开始结束
        phaser.arriveAndDeregister();
        System.out.format("%s: Work completed.", Thread.currentThread().getName());
    }
}
