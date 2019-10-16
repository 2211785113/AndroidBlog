package com.example.ruru.sophiesblog.java.threaddemo.sleep;

/**
 * Created by SophieRu on 2019/7/7
 */
public class UseSleep extends Thread {

    @Override
    public void run() {
        super.run();

        System.out.println("isInterrupted=" + isInterrupted());

        while (!isInterrupted()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("isInterrupted=" + isInterrupted());
                interrupt();
                System.out.println("isInterrupted=" + isInterrupted());
            }
        }
    }
}
