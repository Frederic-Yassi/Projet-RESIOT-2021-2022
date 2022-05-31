package com.Socket;

import static com.demo.ApirestApplication.knx;
import static com.demo.ApirestApplication.work;

public class simulKnx2 extends Thread {
    void Todo() throws InterruptedException {
        while (work){
            knx.led1= true;
            knx.led2= false;
            knx.led3= false;
            knx.led4= false;
            Thread.sleep(500);
            knx.led1= false;
            knx.led2= false;
            knx.led3= false;
            knx.led4= true;
            Thread.sleep(500);
            knx.led1= false;
            knx.led2= false;
            knx.led3= true;
            knx.led4= false;
            Thread.sleep(500);
            knx.led1= false;
            knx.led2= true;
            knx.led3= false;
            knx.led4= false;
            Thread.sleep(500);
        }
    }

    @Override
    public void run(){
        try {
            Todo();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
