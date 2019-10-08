package com.example.ruru.sophiesblog.design_model.create.single_factory.example3;

import java.io.Serializable;

public class ReimburseDto implements Serializable {

    private String name;

    public ReimburseDto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\'" + name + "\'" +
                '}';
    }
}
