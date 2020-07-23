package com.example.ruru.sophiesblog.android.service.local;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public class ClientServiceA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_service);

        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                ServiceA.MyBind myBind = (ServiceA.MyBind) service;
                myBind.getString();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        Intent intent = new Intent(this, ServiceA.class);
//        startService(intent);
//        stopService(intent);

        bindService(intent, connection, BIND_AUTO_CREATE);
//        unbindService(connection);
    }
}
