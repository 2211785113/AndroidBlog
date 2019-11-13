package com.example.ruru.sophiesblog.android.service.remote.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.android.service.remote.server.MyAIDLService;

public class ClientServiceB extends AppCompatActivity {

    private ServiceConnection connection = new ServiceConnection() {

        private MyAIDLService myAIDLService;

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取MyAIDLService对象
            myAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                String str = myAIDLService.getString();
                Log.d(getClass().getName(), "onServiceConnected:得到的string为=== " + str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAIDLService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_service_b);

        Intent intent = new Intent();
        intent.setAction("com.example.ruru.sophiesblog.android.service.remote.server.ServiceB");
        //从 Android 5.0开始 隐式Intent绑定服务的方式已不能使用,所以这里需要设置Service所在服务端的包名
        intent.setPackage("com.example.ruru.sophiesblog.android.service.remote.server");
        bindService(intent, connection, BIND_AUTO_CREATE);

//        unbindService(connection);
    }
}
