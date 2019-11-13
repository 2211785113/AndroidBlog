package com.example.ruru.sophiesblog.android.service.remote.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.ruru.sophiesblog.android.service.remote.server.MyAIDLService;

public class ServiceB extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinds();
    }

    private class MyBinds extends MyAIDLService.Stub {
        @Override
        public String getString() throws RemoteException {
            String string = "我是从服务器返回的";
            return string;
        }
    }
}
