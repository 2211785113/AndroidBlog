package com.example.ruru.sophiesblog.android.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ruru.sophiesblog.R;

public class BaseActivityDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_demo);

        LogUtil.d("onCreate+++");
        LogUtil.d("onCreate:" + getClass().getSimpleName() + " TaskId:" + getTaskId() + " hashcode:" + hashCode());

        dumpTaskAffinity();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.d("onNewIntent+++");
        LogUtil.d("onNewIntent:" + getClass().getSimpleName() + " TaskId:" + getTaskId() + " hashcode:" + hashCode());

        dumpTaskAffinity();
    }

    private void dumpTaskAffinity() {
        try {
            ActivityInfo activityInfo = this.getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            LogUtil.d("taskAffinity:" + activityInfo.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
