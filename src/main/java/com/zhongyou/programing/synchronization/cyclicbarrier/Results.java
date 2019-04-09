package com.zhongyou.programing.synchronization.cyclicbarrier;

/**
 * <p>版权所有： 版权所有(C)2011-2099</p>
 * <p>公   司： 口袋购物 </p>
 * <p>内容摘要： 保存矩阵中每行找到指定数字的次数</p>
 * <p>其他说明： </p>
 * <p>完成日期： 2019-04-08 23:52</p>
 *
 * @author wangqiming
 * @version v1.0
 */
public class Results {

    private int data[];

    /**
     * @param size 指定data数组的长度
     */
    public Results(int size) {
        this.data = new int[size];
    }

    public void setData(int position, int value) {
        this.data[position] = value;
    }

    public int[] getData() {
        return data;
    }
}
