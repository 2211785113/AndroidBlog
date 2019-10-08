package com.example.ruru.sophiesblog.design_model.create.builder.example1;

public class Computer {
    private String mCpu;//cpu
    private String mMainboard;//主板
    private String mRam;//内存

    public void setmCpu(String mCpu) {
        this.mCpu = mCpu;
    }

    public void setmMainboard(String mMainboard) {
        this.mMainboard = mMainboard;
    }

    public void setmRam(String mRam) {
        this.mRam = mRam;
    }

    @Override
    public String toString() {
        return "{" +
                "\"mCpu\":\'" + mCpu + "\'" +
                ", \"mMainboard\":\'" + mMainboard + "\'" +
                ", \"mRam\":\'" + mRam + "\'" +
                '}';
    }
}
