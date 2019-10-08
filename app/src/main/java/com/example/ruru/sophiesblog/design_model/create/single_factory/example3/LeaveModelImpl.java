package com.example.ruru.sophiesblog.design_model.create.single_factory.example3;

public class LeaveModelImpl implements Model {

    public static final String LEAVE_MODEL_NAME = "leave";

    @Override
    public void loadData(OnListenerParadigm listenerParadigm, String... args) {
        System.out.println("请求参数=" + args[0]);
        listenerParadigm.onSuccess(new LeaveDto("回调数据2"));
    }
}
