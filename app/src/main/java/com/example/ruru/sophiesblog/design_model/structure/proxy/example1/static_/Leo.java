package com.example.ruru.sophiesblog.design_model.structure.proxy.example1.static_;

import com.example.ruru.sophiesblog.design_model.structure.proxy.example1.public_.MakeFactory;

/**
 * 代理对象
 */
public class Leo implements MakeFactory {

    private MakeFactory factory;

    public Leo(MakeFactory factory) {
        this.factory = factory;
    }

    @Override
    public void saleThings() {
        factory.saleThings();
    }
}
