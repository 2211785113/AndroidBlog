package com.example.ruru.sophiesblog.design_model.structure.proxy.example1.public_;

/**
 * 真实对象
 */
public class BFactory implements MakeFactory {

    @Override
    public void saleThings() {
        System.out.println("B工厂在卖东西呢");
    }
}
