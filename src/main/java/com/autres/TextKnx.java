package com.autres;


import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.process.ProcessCommunicator;

public class TextKnx extends Thread {
    public static int vitesse = TextKnx.vitesse;
    public static boolean sense = TextKnx.sense;
    public static boolean start = TextKnx.start;

    private static boolean light1 = false;
    private static boolean light2 = false;
    private static boolean light3 = false;
    private static boolean light4 = false;

    @Override
    public void run() {

        try{
            //Thread.sleep(i);
            start = true;
            while (start){
                if( sense == true){
                    light1 = true;
                    light2 = false;
                    light3 = false;
                    light4 = false;


                    System.out.println(light4);
                    Thread.sleep(vitesse);

                    System.out.println(light1);
                    light1 = false;
                    System.out.println(light1);
                    light2 = true;
                    System.out.println(light2);
                    light3 = false;
                    System.out.println(light3);
                    light4 = false;
                    System.out.println(light4);
                    Thread.sleep(vitesse);

                    System.out.println(light1);
                    light1 = false;
                    System.out.println(light1);
                    light2 = false;
                    System.out.println(light2);
                    light3 = true;
                    System.out.println(light3);
                    light4 = false;
                    System.out.println(light4);
                    Thread.sleep(vitesse);

                    System.out.println(light1);
                    light1 = false;
                    System.out.println(light1);
                    light2 = false;
                    System.out.println(light2);
                    light3 = false;
                    System.out.println(light3);
                    light4 = true;
                    System.out.println(light4);
                    Thread.sleep(vitesse);

                }
                else {
                    System.out.println(light1);
                    light1 = false;
                    System.out.println(light1);
                    light2 = false;
                    System.out.println(light2);
                    light3 = false;
                    System.out.println(light3);
                    light4 = true;
                    System.out.println(light4);
                    Thread.sleep(vitesse);

                    System.out.println(light1);
                    light1 = false;
                    System.out.println(light1);
                    light2 = false;
                    System.out.println(light2);
                    light3 = true;
                    System.out.println(light3);
                    light4 = false;
                    System.out.println(light4);
                    Thread.sleep(vitesse);

                    System.out.println(light1);
                    light1 = false;
                    System.out.println(light1);
                    light2 = true;
                    System.out.println(light2);
                    light3 = false;
                    System.out.println(light3);
                    light4 = false;
                    System.out.println(light4);
                    Thread.sleep(vitesse);

                    System.out.println(light1);
                    light1 = true;
                    System.out.println(light1);
                    light2 = false;
                    System.out.println(light2);
                    light3 = false;
                    System.out.println(light3);
                    light4 = false;
                    System.out.println(light4);
                    Thread.sleep(vitesse);
                }

            }

        } catch (InterruptedException e) {
            System.out.println("Error accessing KNX datapoint: " + e.getMessage());
        }

    }
}

