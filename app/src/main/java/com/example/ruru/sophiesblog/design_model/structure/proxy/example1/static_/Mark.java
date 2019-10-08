package com.example.ruru.sophiesblog.design_model.structure.proxy.example1.static_;

import com.example.ruru.sophiesblog.design_model.structure.proxy.example1.public_.MakeFactory;

/**
 * 代理对象
 */
public class Mark implements MakeFactory {

    private MakeFactory factory;

    public Mark(MakeFactory factory) {
        this.factory = factory;
    }

    @Override
    public void saleThings() {
        factory.saleThings();
    }
}
