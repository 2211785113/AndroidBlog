package com.example.ruru.sophiesblog.java.threaddemo.waitandnotify;

import android.util.Log;

public class ExpressBean {

    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/

    public ExpressBean() {
    }

    public ExpressBean(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public synchronized void changeKm() {
        this.km = 1000;
        Log.d(getClass().getName(), "changeKm: km = " + this.km);
        notifyAll();//注意：这里是要把 site 线程和 km 线程都唤醒。
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public synchronized void changeSite() {
        this.site = "BeiJing";
        notifyAll();//注意：这里是要把 site 线程和 km 线程都唤醒。
    }

    public synchronized void waitKm() {
        Log.d(getClass().getName(), "waitKm: km = " + this.km);
        while (this.km < 100) {
            Log.d(getClass().getName(), "waitKm: enter waitKm");
            try {
                wait();//持有对象锁，调用完后释放锁。
                System.out.println("waitKm");
                Log.d("aaa", "check km thread[" + Thread.currentThread().getName() + "] is be notifyed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d(getClass().getName(), "the km is" + this.km + ",I will change db.");
    }

    public synchronized void waitSite() {
        Log.d(getClass().getName(), "waitSite: site = " + this.site);
        while (CITY.equals(this.site)) {
            Log.d(getClass().getName(), "waitSite: enter waitSite");
            try {
                wait();//持有对象锁，调用完后释放锁。
                System.out.println("waitSite");
                Log.d("aaa", "check site thread[" + Thread.currentThread().getName() + "] is be notifyed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d(getClass().getName(), "the site is" + this.site + ",I will call user.");
    }
}
