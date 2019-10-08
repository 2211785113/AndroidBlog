package com.example.ruru.sophiesblog.design_model.create.builder.example4;

public class EventBus1 {

    private static EventBus1 defaultInstance = new EventBus1();

    public static EventBus1 getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus1.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus1();
                }
            }
        }
        return defaultInstance;
    }
}
