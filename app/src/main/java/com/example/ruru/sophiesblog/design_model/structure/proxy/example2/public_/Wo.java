package com.example.ruru.sophiesblog.design_model.structure.proxy.example2.public_;

import com.example.ruru.sophiesblog.design_model.structure.proxy.example2.public_.IShop;

/**
 * 真实主题类
 */
public class Wo implements IShop {
    @Override
    public void buy() {
        System.out.println("购买");
    }
}
