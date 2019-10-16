package com.example.ruru.sophiesblog.java.threaddemo.readWriteLock;

import com.example.ruru.sophiesblog.java.threaddemo.tools.SleepTools;

import java.util.Random;

public class GoodsOperate {

    //读操作
    public static class GetThread implements Runnable {

        private GoodsService goodsService;

        public GetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {//操作100次
                goodsService.getNum();
            }
            System.out.println(Thread.currentThread().getName() + "读商品数据耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    //写操作
    public static class SetThread implements Runnable {

        private GoodsService goodsService;

        public SetThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Random r = new Random();
            for (int i = 0; i < 10; i++) {//操作10次
                SleepTools.millisecond(50);
                goodsService.setNum(r.nextInt(10));
            }
            System.out.println(Thread.currentThread().getName() + "写商品数据耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
