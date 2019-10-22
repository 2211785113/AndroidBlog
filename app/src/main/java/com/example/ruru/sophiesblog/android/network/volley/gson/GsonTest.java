package com.example.ruru.sophiesblog.android.network.volley.gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;
import com.example.ruru.sophiesblog.android.network.volley.bean.Stu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_test);

        Gson gson = new Gson();

        //反序列化：json->对象
        int i = gson.fromJson("100", int.class);              //100
        double d = gson.fromJson("\"99.99\"", double.class);  //99.99
        boolean b = gson.fromJson("true", boolean.class);     // true
        String str = gson.fromJson("String", String.class);   // String

        String jsonStrings = "{\"id\":3,\"name\":\"小晶\",\"gender\":\"女\",\"age\":24}";
        Stu stu = gson.fromJson(jsonStrings, Stu.class);

        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        String[] strings = gson.fromJson(jsonArray, String[].class);//String[].class不能换成List<String>.class
        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {
        }.getType());//TypeToken实现对泛型的支持

        //序列化：对象->json
        String jsonNumber = gson.toJson(100);       // 100
        String jsonBoolean = gson.toJson(false);    // false
        String jsonString = gson.toJson("String"); //"String"

        String json = gson.toJson(new Stu(1, "小明", "男", 21));
    }
}
