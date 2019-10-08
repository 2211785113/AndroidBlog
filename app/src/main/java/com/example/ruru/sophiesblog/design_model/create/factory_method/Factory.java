package com.example.ruru.sophiesblog.design_model.create.factory_method;

import com.example.ruru.sophiesblog.design_model.create.single_factory.example2.Computer;

public abstract class Factory {
    public abstract <T extends Computer> T createComputer(Class<T> clz) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
}
