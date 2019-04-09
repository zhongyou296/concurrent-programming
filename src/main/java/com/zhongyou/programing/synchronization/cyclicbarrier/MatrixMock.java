package com.zhongyou.programing.synchronization.cyclicbarrier;

import java.util.Random;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 随机数构成的矩阵</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-08 23:47</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class MatrixMock {

    private int data[][];

    /**
     * 初始化一个随机二维数组
     *
     * @param size   行数
     * @param length 长度
     * @param number 要查找的数字
     */
    public MatrixMock(int size, int length, int number) {
        int count = 0;
        data = new int[size][length];
        Random random = new Random();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < length; ++j) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    ++count;
                }
            }
        }
        System.out.format("last count=%d, number=%d\n", count, number);
    }

    /**
     * 获取某一行元素
     *
     * @param row
     * @return
     */
    public int[] getRows(int row) {
        if (row >= 0 && row < data.length) {
            return data[row];
        }
        return null;
    }
}
