package com.example.ruru.sophiesblog.design_model.create.factory_method;

import com.example.ruru.sophiesblog.design_model.create.single_factory.example2.Computer;

public class ComputerFactory extends Factory {

    /**
     * 通过反射来生产电脑
     */
    @Override
    public <T extends Computer> T createComputer(Class<T> clz) {
        Computer computer = null;
        String clzName = clz.getName();

        try {
            //通过反射来生产不同厂家的计算机
            computer = (Computer) Class.forName(clzName).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) computer;
    }
}
