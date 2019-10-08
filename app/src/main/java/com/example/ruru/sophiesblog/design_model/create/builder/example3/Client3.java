package com.example.ruru.sophiesblog.design_model.create.builder.example3;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

public class Client3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        new AlertDialog.Builder(this).setTitle("标题").setIcon(R.mipmap.ic_launcher).setMessage("测试").show();

        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("标题").setIcon(R.mipmap.ic_launcher).setMessage("测试").create();
        dialog.show();

    }
}
