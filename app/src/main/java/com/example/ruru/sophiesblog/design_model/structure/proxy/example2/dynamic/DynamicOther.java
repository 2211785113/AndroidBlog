package com.example.ruru.sophiesblog.design_model.structure.proxy.example2.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类
 */
public class DynamicOther implements InvocationHandler {

    private Object obj;

    public DynamicOther(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(obj, args);
        if (method.getName().equals("buy")) {
            System.out.println("wo 在买买买");
        }
        return result;
    }
}
