package com.example.ruru.sophiesblog.java.threaddemo.condition;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExpressCond {
    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/

    private Lock lock = new ReentrantLock();//重入锁
    private Condition kmCondi = lock.newCondition();
    private Condition siteCondi = lock.newCondition();

    public ExpressCond() {
    }

    public ExpressCond(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 加锁通知：变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public void changeKm() {
        lock.lock();
        try {
            this.km = 101;
            kmCondi.signal();
        } finally {
            lock.unlock();
        }
    }

    /* 加锁通知：变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public void changeSite() {
        lock.lock();
        try {
            this.site = "BeiJing";
            siteCondi.signal();
        } finally {
            lock.unlock();
        }
    }

    /* 加锁等待：当快递的里程数大于100时更新数据库*/
    public void waitKm() {
        Log.d(getClass().getName(), "waitKm: ");
        lock.lock();
        try {
            while (this.km < 100) {
                Log.d(getClass().getName(), "waitKm: enter");
                try {
                    kmCondi.await();
                    Log.d(getClass().getName(), "check km thread[" + Thread.currentThread().getId() + "] is be notified");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.d(getClass().getName(), "waitKm:e= " + e);
                }
            }
        } finally {
            lock.unlock();
        }
        Log.d(getClass().getName(), "the Km is " + this.km + ",I will change db");
    }

    /* 加锁等待：当快递到达目的地时通知用户*/
    public void waitSite() {
        Log.d(getClass().getName(), "waitSite: ");
        lock.lock();
        try {
            while (CITY.equals(this.site)) {
                Log.d(getClass().getName(), "waitSite:enter ");
                try {
                    siteCondi.await();
                    Log.d(getClass().getName(), "check site thread[" + Thread.currentThread().getId() + "] is be notifed.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.d(getClass().getName(), "waitSite:e= " + e);
                }
            }
        } finally {
            lock.unlock();
        }
        Log.d(getClass().getName(), "the site is " + this.site + ",I will call user");
    }
}
