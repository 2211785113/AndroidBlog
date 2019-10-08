package com.example.ruru.sophiesblog.design_model.create.single_factory.example1;

public class Factory1 {

    public Human createHuman(String type) {
        Human human = null;
        switch (type) {
            case "man":
                human = new Man();
                break;
            case "woman":
                human = new Woman();
                break;
        }
        return human;
    }
}
