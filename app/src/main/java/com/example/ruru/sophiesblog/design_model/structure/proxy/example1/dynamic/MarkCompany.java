package com.example.ruru.sophiesblog.design_model.structure.proxy.example1.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理对象：取代了Leo和Mark。
 */
public class MarkCompany implements InvocationHandler {

    private Object factory;//真实对象

    public void setFactory(Object factory) {
        this.factory = factory;
    }

    public Object getFactory() {
        return factory;
    }

    /**
     * 通过Proxy获得动态代理对象：传入真实对象类加载器和真实对象方法。
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(factory.getClass().getClassLoader(), factory.getClass().getInterfaces(), this);
    }

    /**
     * 通过动态代理对象方法进行增强
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用factory的方法和参数
        Object obj = method.invoke(factory, args);
        return obj;
    }
}
