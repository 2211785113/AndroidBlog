package com.example.ruru.sophiesblog.java.threaddemo.readWriteLock;


import com.example.ruru.sophiesblog.java.threaddemo.tools.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 显式读写锁
 */
public class UseReadWriteLock implements GoodsService {

    private GoodsInfoBean goodsInfo;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock getLock = lock.readLock();//读锁
    private final Lock setLock = lock.writeLock();//写锁

    public UseReadWriteLock(GoodsInfoBean goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    /**
     * 用户读：返回goodsInfo
     */
    @Override
    public GoodsInfoBean getNum() {
        getLock.lock();
        try {
            SleepTools.millisecond(5);
            return goodsInfo;
        } finally {
            getLock.unlock();
        }
    }

    /**
     * 用户写：修改bean类的属性
     */
    @Override
    public void setNum(int number) {
        setLock.lock();
        try {
            SleepTools.millisecond(5);
            goodsInfo.changeNumber(number);
        } finally {
            setLock.unlock();
        }
    }

}
