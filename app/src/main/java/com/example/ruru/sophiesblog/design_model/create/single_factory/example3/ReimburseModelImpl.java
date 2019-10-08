package com.example.ruru.sophiesblog.design_model.create.single_factory.example3;

public class ReimburseModelImpl implements Model<ReimburseDto> {

    public static final String REIMBURSE_MODEL_NAME = "reimburse";

    @Override
    public void loadData(OnListenerParadigm<ReimburseDto> listenerParadigm, String... args) {
        System.out.println("请求参数=" + args[0]);
        listenerParadigm.onSuccess(new ReimburseDto("回调数据1"));
    }
}
