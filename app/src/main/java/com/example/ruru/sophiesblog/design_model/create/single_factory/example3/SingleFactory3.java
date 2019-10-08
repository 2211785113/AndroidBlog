package com.example.ruru.sophiesblog.design_model.create.single_factory.example3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruru.sophiesblog.R;

import static com.example.ruru.sophiesblog.design_model.create.single_factory.example3.LeaveModelImpl.LEAVE_MODEL_NAME;
import static com.example.ruru.sophiesblog.design_model.create.single_factory.example3.ReimburseModelImpl.REIMBURSE_MODEL_NAME;

public class SingleFactory3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_factory3);

        PresenterImpl<ReimburseDto> presenter = new PresenterImpl<ReimburseDto>(REIMBURSE_MODEL_NAME);
        presenter.getData("参数1");

        PresenterImpl<LeaveDto> presenter1 = new PresenterImpl<>(LEAVE_MODEL_NAME);
        presenter1.getData("参数2");
    }
}
