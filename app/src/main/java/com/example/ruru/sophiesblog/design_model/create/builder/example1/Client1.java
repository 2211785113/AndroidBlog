package com.example.ruru.sophiesblog.design_model.create.builder.example1;

import android.os.Bundle;
import android.util.Log;

import com.example.ruru.sophiesblog.R;

import androidx.appcompat.app.AppCompatActivity;

public class Client1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_computer);

        Builder mBuilder = new ComputerBuilder();
        Director mDirector = new Director(mBuilder);
        Computer computer = mDirector.createComputer("i7-6700", "华擎玩家至尊", "三星DDR4");
        Log.d(getClass().getName(), "onCreate:res= " + computer);


    }
}
