package com.Socket;

import com.demo.ApirestApplication;

import static com.demo.ApirestApplication.work;

public class Mythread extends Thread {
    void myfonction() throws InterruptedException {
            /*while (ApirestApplication.work == false) {
                System.out.println("En attente de commencer");
                Thread.sleep(2000);
            }
            ;*/
            for (int i = 0; i < 10000; i++) {
                Thread.sleep(2000);
                System.out.println(i);
                if (ApirestApplication.work == false) {
                    this.interrupt();
                }
            }
    }
    @Override
    public void run() {
        try {
            myfonction();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}