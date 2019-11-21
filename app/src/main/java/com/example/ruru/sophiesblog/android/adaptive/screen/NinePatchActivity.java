package com.example.ruru.sophiesblog.android.adaptive.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

/**
 * 布局组件适配：.9图，不过还是没明白.9图的作用，可能是AS 3.2取消了。
 */
public class NinePatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_patch);
    }
}
