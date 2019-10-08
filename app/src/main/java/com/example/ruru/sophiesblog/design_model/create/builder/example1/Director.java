package com.example.ruru.sophiesblog.design_model.create.builder.example1;

public class Director {

    private Builder mBuild;

    public Director(Builder build) {
        this.mBuild = build;
    }

    public Computer createComputer(String cpu, String mainboard, String ram) {

        //规范建造流程
        mBuild.buildCpu(cpu);
        mBuild.buildMainboard(mainboard);
        mBuild.buildRam(ram);

        return mBuild.create();
    }
}
