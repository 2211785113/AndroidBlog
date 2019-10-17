package com.example.ruru.sophiesblog.java.threaddemo.sleep;

/**
 * Created by SophieRu on 2019/7/7
 */
public class UseSleep extends Thread {

    @Override
    public void run() {
        super.run();

        System.out.println("isInterrupted=" + isInterrupted());//false

        while (!isInterrupted()) {//true
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {//抛出异常会将isInterrupted由true改为false
                System.out.println("isInterrupted=" + isInterrupted());//false
                interrupt();//false改为true
                System.out.println("isInterrupted=" + isInterrupted());//true
            }
        }
    }
}
