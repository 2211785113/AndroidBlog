package com.example.ruru.sophiesblog.java.extends_.abstractAndInterface.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getContentViewId(), container);

        initMembersView(savedInstanceState);

        return view;
    }

    protected abstract void initMembersView(Bundle savedInstanceState);

    public abstract int getContentViewId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
