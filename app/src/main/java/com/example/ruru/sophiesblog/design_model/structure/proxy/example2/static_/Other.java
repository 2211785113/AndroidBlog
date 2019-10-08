package com.example.ruru.sophiesblog.design_model.structure.proxy.example2.static_;

import com.example.ruru.sophiesblog.design_model.structure.proxy.example2.public_.IShop;

/**
 * 代理类
 */
public class Other implements IShop {

    private IShop mShop;

    public Other(IShop shop) {
        mShop = shop;
    }

    @Override
    public void buy() {
        mShop.buy();
    }
}
