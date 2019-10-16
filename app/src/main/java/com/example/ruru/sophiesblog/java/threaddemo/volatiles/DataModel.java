package com.example.ruru.sophiesblog.java.threaddemo.volatiles;

import android.util.Log;

/**
 * Created by SophieRu on 2019/7/27
 */
public class DataModel {

    private volatile static DataModel dataModel = new DataModel();

    public DataModel() {

    }

    public static DataModel getInstance() {
        return dataModel;
    }

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void initCount() {
        count++;
    }
}

