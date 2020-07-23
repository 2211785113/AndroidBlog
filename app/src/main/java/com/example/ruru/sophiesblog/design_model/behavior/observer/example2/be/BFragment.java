package com.example.ruru.sophiesblog.design_model.behavior.observer.example2.be;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruru.sophiesblog.R;

import androidx.fragment.app.Fragment;

//具体观察者
public class BFragment extends Fragment implements OnRefreshListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onRefresh() {
        System.out.println("B is refreshing");
    }
}
