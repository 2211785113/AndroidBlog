package com.example.ruru.sophiesblog.design_model.create.single_factory.example3;

import java.io.Serializable;

public class LeaveDto implements Serializable {
    private String name;

    public LeaveDto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\'" + name + "\'" +
                '}';
    }
}
