package com.example.ruru.sophiesblog.java.extends_.reflect;

import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 反射-反序列化运用
 */
public class Deserialization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deserialization);

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("aa.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                ois.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
