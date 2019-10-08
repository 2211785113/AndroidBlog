package com.example.ruru.sophiesblog.design_model.create.single_factory.example3;

import android.view.Display;

public class ModelFactory<T> {

    /*单例模式*/
    private ModelFactory() {

    }

    private static ModelFactory instance = new ModelFactory();

    public static ModelFactory getInstance() {
        return instance;
    }

    public Model<T> createModel(String modelName) {
        Model model = null;
        switch (modelName) {
            case "reimburse":
                model = new ReimburseModelImpl();
                break;
            case "leave":
                model = new LeaveModelImpl();
                break;
            default:
                break;
        }
        return model;
    }
}
