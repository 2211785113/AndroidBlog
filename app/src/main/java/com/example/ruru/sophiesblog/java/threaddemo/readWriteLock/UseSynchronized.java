package com.example.ruru.sophiesblog.java.threaddemo.readWriteLock;

import com.example.ruru.sophiesblog.java.threaddemo.tools.SleepTools;

/**
 * 内置同步锁
 */
public class UseSynchronized implements GoodsService {

    private GoodsInfoBean goodsInfo;

    public UseSynchronized(GoodsInfoBean goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfoBean getNum() {
        SleepTools.millisecond(5);
        return goodsInfo;
    }

    @Override
    public synchronized void setNum(int number) {
        SleepTools.millisecond(5);
        goodsInfo.changeNumber(number);
    }

}
