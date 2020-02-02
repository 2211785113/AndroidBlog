package com.example.ruru.sophiesblog.java.objectAndClass.house;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public class ClientRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_room);

        //多态
        Room mRoom = new ThisRoom();
        mRoom.openWater();
        mRoom.openElectricity();
    }
}
