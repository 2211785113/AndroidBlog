package com.example.ruru.sophiesblog.java.threaddemo.readWriteLock;

/**
 * 商品实体类
 */
public class GoodsInfoBean {
    private final String name;
    private double totalMoney;//总销售额
    private int storeNumber;//库存数

    public GoodsInfoBean(String name, int totalMoney, int storeNumber) {
        this.name = name;
        this.totalMoney = totalMoney;
        this.storeNumber = storeNumber;
    }

    //获取总销售额
    public double getTotalMoney() {
        return totalMoney;
    }

    //获取库存数
    public int getStoreNumber() {
        return storeNumber;
    }

    //改变库存数
    public void changeNumber(int sellNumber) {
        this.totalMoney += sellNumber * 25;
        this.storeNumber -= sellNumber;
    }
}
