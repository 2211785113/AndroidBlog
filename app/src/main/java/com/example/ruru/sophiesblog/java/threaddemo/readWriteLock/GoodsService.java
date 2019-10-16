package com.example.ruru.sophiesblog.java.threaddemo.readWriteLock;

/**
 * 抽象出了：
 * UseReadWriteLock & UseSynchronized
 * 两个类里边的操作：getNum & setNum。
 */
public interface GoodsService {

    GoodsInfoBean getNum();//获得商品的信息

    void setNum(int number);//设置商品的数量
}
