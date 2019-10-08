package com.example.ruru.sophiesblog.design_model.create.single_factory.example3;

public interface OnListenerParadigm<T> {

    /**
     * 成功时回调
     */
    void onSuccess(T data);

    /**
     * 失败时回调
     */
    void onError();
}
