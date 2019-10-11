package com.example.ruru.sophiesblog.java.dataType;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ruru.sophiesblog.R;

/**
 * boolean：密码的显示与隐藏
 */
public class ABoolean extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "info";
    private EditText et_name;
    private ImageView img_clear;
    private ImageView img_name;
    boolean isChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboolean);

        initView();
        initBtn();
    }

    private void initBtn() {
        //刚开始隐藏密码
        et_name.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        img_clear = (ImageView) findViewById(R.id.img_clear);
        img_name = (ImageView) findViewById(R.id.img_name);
        img_clear.setOnClickListener(this);
        img_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_clear:
                et_name.setText("");
                break;
            case R.id.img_name:
                changeImg();
                break;
            default:
                break;
        }
    }

    private void changeImg() {
        if (isChanged) {
            //隐藏密码
//            img_name.setImageDrawable(getResources().getDrawable(R.mipmap.img_nodisplay_pass));
            et_name.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            //显示密码
//            img_name.setImageDrawable(getResources().getDrawable(R.mipmap.img_display_pass));
            et_name.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        isChanged = !isChanged;
    }

}
