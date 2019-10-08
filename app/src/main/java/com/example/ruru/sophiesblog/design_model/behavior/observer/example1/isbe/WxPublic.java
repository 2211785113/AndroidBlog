package com.example.ruru.sophiesblog.design_model.behavior.observer.example1.isbe;

import com.example.ruru.sophiesblog.design_model.behavior.observer.example1.be.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体被观察者：公众号
 */
public class WxPublic implements Subject {

    private List<Observer> weixinUserlist = new ArrayList<Observer>();//存储订阅公众号的微信用户

    @Override
    public void attach(Observer observer) {
        weixinUserlist.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weixinUserlist.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : weixinUserlist) {
            observer.update(message);
        }
    }
}
