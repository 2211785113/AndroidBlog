package com.example.ruru.sophiesblog.design_model.behavior.observer.example2.be;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruru.sophiesblog.R;

//具体观察者
public class AFragment extends Fragment implements OnRefreshListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onRefresh() {
        System.out.println("A is refreshing");
    }
}
