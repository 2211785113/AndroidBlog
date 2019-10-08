package com.example.ruru.sophiesblog.design_model.behavior.observer.example1.isbe;

import com.example.ruru.sophiesblog.design_model.behavior.observer.example1.be.Observer;

/**
 * 抽象被观察者
 */
public interface Subject {

    void attach(Observer observer);//增加订阅者

    void detach(Observer observer);//删除订阅者

    void notify(String message);//通知订阅者更新消息
}
