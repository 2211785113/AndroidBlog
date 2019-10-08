package com.example.ruru.sophiesblog.design_model.create.single_factory.example2;

public class Factory2 {

    public Computer createComputer(String type) {
        Computer computer = null;
        switch (type) {
            case "hp":
                computer = new HpComputer();
                break;
            case "lenova":
                computer = new LenovaComputer();
                break;
            case "asus":
                computer = new AsusComputer();
                break;
        }
        return computer;
    }
}
