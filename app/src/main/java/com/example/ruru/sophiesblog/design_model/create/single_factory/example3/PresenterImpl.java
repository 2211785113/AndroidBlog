package com.example.ruru.sophiesblog.design_model.create.single_factory.example3;

import android.view.MotionEvent;
import android.view.View;

public class PresenterImpl<T> implements OnListenerParadigm<T> {

    private View view;
    private Model<T> model;

    public PresenterImpl(String modelName) {
//        this.view = view;
        this.model = ModelFactory.getInstance().createModel(modelName);//单例模式+简单工厂模式
    }

    public void getData(String... args) {
        model.loadData(this, args);//接口回调
    }

    /**
     * 成功时回调
     */
    @Override
    public void onSuccess(T data) {
        System.out.println("网络请求数据成功，回调数据=" + data);
    }

    /**
     * 失败时回调
     */
    @Override
    public void onError() {
        System.out.println("网络请求数据失败");
    }
}
