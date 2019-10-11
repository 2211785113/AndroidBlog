package com.example.ruru.sophiesblog.java.extends_.superDemo;

/**
 * super的用法
 *
 * 经理
 */
public class Manager extends Employee {

    public int getSalary() {
        int res = super.getSalary();
        return res;
    }
}
