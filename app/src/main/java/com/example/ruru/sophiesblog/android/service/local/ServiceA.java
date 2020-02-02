package com.example.ruru.sophiesblog.android.service.local;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServiceA extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(getClass().getName(), "onCreate:+++ ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(getClass().getName(), "onStartCommand:+++ ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    public class MyBind extends Binder {
        public void getString() {
            Log.d(getClass().getName(), "getString: ");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(getClass().getName(), "onDestroy:+++ ");
    }
}
