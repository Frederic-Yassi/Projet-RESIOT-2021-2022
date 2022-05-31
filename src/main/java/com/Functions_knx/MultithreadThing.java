package com.Functions_knx;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.link.KNXNetworkLink;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

import static com.demo.ApirestApplication.knx;

public class MultithreadThing extends Thread {
    private final ProcessCommunicator pc;

    //private final KNXNetworkLink knxLink;

    //final String remoteHost;
    MultithreadThing(ProcessCommunicator pc){
        this.pc = pc;
    }


    // Address of your KNXnet/IP server. Replace the IP host or address as necessary.
    //private static final String remoteHost = "192.168.0.202";


    // We will read a boolean from the KNX datapoint with this light4address, replace the address as necessary.
    // Make sure this datapoint exists, otherwise you will get a read timeout!



    //static ProcessCommunicator pc;
    private static final String light1 = "0/0/1";
    private static final String light2 = "0/0/2";
    private static final String light3 = "0/0/3";
    private static final String light4 = "0/0/4";


    private static final String lightState1 = "1/0/1";
    private static final String lightState2 = "1/0/2";
    private static final String lightState3 = "1/0/3";
    private static final String lightState4 = "1/0/4";



    @Override
    public void run() {
        knx.vitesse = 700;
        knx.sense = true;
        knx.start = false ;

        knx.motif = 0;

        try{
            //pc = new ProcessCommunicatorImpl(knxLink);
            System.out.println("read boolean value from datapoint " + light4);
            boolean value = pc.readBool(new GroupAddress(light4));

            //System.out.println("datapoint " + light4 + " value = " + value);

            boolean value1 = pc.readBool(new GroupAddress(lightState1));
            knx.led1=value1;
            //System.out.println("datapoint " + lightState1 + " value = " + value1);

            boolean value2 = pc.readBool(new GroupAddress(lightState2));
            knx.led2=value2 ;
            //System.out.println("datapoint " + lightState2 + " value = " + value2);

            boolean value3 = pc.readBool(new GroupAddress(lightState3));
            knx.led3=value3 ;
            //System.out.println("datapoint " + lightState3 + " value = " + value3);

            boolean value4 = pc.readBool(new GroupAddress(lightState4));
            knx.led4=value4 ;
            //System.out.println("datapoint " + lightState4 + " value = " + value4);

            pc.write(new GroupAddress(light1), true);
            pc.write(new GroupAddress(light2), false);
            pc.write(new GroupAddress(light3), false);
            pc.write(new GroupAddress(light4), false);
            //Thread.sleep(i);
            while(knx.start==false){
                System.out.println("knx en attente ... ");
                Thread.sleep(2);
            }
            while (knx.start) {
                if(knx.motif==0){
                    if (knx.sense) {
                        System.out.println("Sens = " + knx.sense);
                        // sens gauche - droite
                        System.err.println(pc);

                        if (knx.start /*&& sense  && value4*/) {
                            pc.write(new GroupAddress(light1), true);
                            pc.write(new GroupAddress(light2), false);
                            pc.write(new GroupAddress(light3), false);
                            pc.write(new GroupAddress(light4), false);
                            Thread.sleep(knx.vitesse);
                        }

                        if (knx.start /*&& sense  && value1*//*&& previous light state =true*/) {
                            pc.write(new GroupAddress(light1), false);
                            knx.led1=false;
                            pc.write(new GroupAddress(light2), true);
                            knx.led2=true;
                            pc.write(new GroupAddress(light3), false);
                            knx.led3=false;
                            pc.write(new GroupAddress(light4), false);
                            knx.led4=false;
                            Thread.sleep(knx.vitesse);
                        }


                        if (knx.start /*&& sense && value2*/) {
                            pc.write(new GroupAddress(light1), false);
                            knx.led1=false;
                            pc.write(new GroupAddress(light2), false);
                            knx.led2=false;
                            pc.write(new GroupAddress(light3), true);
                            knx.led3=true;
                            pc.write(new GroupAddress(light4), false);
                            knx.led3=false;
                            Thread.sleep(knx.vitesse);
                        }


                        if (knx.start /*&& sense  && value3*/) {
                            pc.write(new GroupAddress(light1), false);
                            knx.led1=false;
                            pc.write(new GroupAddress(light2), false);
                            knx.led3=false;
                            pc.write(new GroupAddress(light3), false);
                            knx.led3=false;
                            pc.write(new GroupAddress(light4), true);
                            knx.led3=true;
                            Thread.sleep(knx.vitesse);
                        }

                    }
                    else {
                        System.out.println("Sens = " + knx.sense);
                        // sens droite - gauche

                        if (knx.start /*&& !sense  &&value1*/) {
                            pc.write(new GroupAddress(light1), false);
                            knx.led1=false;
                            pc.write(new GroupAddress(light2), false);
                            knx.led2=false;
                            pc.write(new GroupAddress(light3), false);
                            knx.led3=false;
                            pc.write(new GroupAddress(light4), true);
                            knx.led3=true;
                            Thread.sleep(knx.vitesse);
                        }

                        if (knx.start /*&& !sense  && value4*/) {
                            pc.write(new GroupAddress(light1), false);
                            knx.led1=false;
                            pc.write(new GroupAddress(light2), false);
                            knx.led2=false;
                            pc.write(new GroupAddress(light3), true);
                            knx.led3=true;
                            pc.write(new GroupAddress(light4), false);
                            knx.led4=false;
                            Thread.sleep(knx.vitesse);
                        }

                        if (knx.start /*&& !sense && value3*/) {
                            pc.write(new GroupAddress(light1), false);
                            knx.led1=false;
                            pc.write(new GroupAddress(light2), true);
                            knx.led2=true;
                            pc.write(new GroupAddress(light3), false);
                            knx.led3=false;
                            pc.write(new GroupAddress(light4), false);
                            knx.led4=false;
                            Thread.sleep(knx.vitesse);
                        }


                        if (knx.start /*&& !sense && value2*/) {
                            pc.write(new GroupAddress(light1), true);
                            knx.led1=true;
                            pc.write(new GroupAddress(light2), false);
                            knx.led2=false;
                            pc.write(new GroupAddress(light3), false);
                            knx.led3=false;
                            pc.write(new GroupAddress(light4), false);
                            knx.led4=false;
                            Thread.sleep(knx.vitesse);
                        }
                    }
                }
                else if( knx.motif==1){
                    pc.write(new GroupAddress(light1), true);
                    knx.led1=true;
                    pc.write(new GroupAddress(light2), false);
                    knx.led2=false;
                    pc.write(new GroupAddress(light3), false);
                    knx.led3=false;
                    pc.write(new GroupAddress(light4), false);
                    knx.led3=false;
                    Thread.sleep(knx.vitesse);

                    pc.write(new GroupAddress(light1), false);
                    knx.led1=false;
                    pc.write(new GroupAddress(light2), false);
                    knx.led2=false;
                    pc.write(new GroupAddress(light3), false);
                    knx.led3=false;
                    pc.write(new GroupAddress(light4), true);
                    knx.led4=true;
                    Thread.sleep(knx.vitesse);

                    pc.write(new GroupAddress(light1), false);
                    knx.led1=false;
                    pc.write(new GroupAddress(light2), true);
                    knx.led2=true;
                    pc.write(new GroupAddress(light3), false);
                    knx.led3=false;
                    pc.write(new GroupAddress(light4), false);
                    knx.led4=false;
                    Thread.sleep(knx.vitesse);

                    pc.write(new GroupAddress(light1), false);
                    knx.led1=false;
                    pc.write(new GroupAddress(light2), false);
                    knx.led2=false;
                    pc.write(new GroupAddress(light3), true);
                    knx.led3=true;
                    pc.write(new GroupAddress(light4), false);
                    knx.led4=false ;
                    Thread.sleep(knx.vitesse);

                }
                else if (knx.motif==2){
                    pc.write(new GroupAddress(light1), true);
                    knx.led1=true;
                    pc.write(new GroupAddress(light2), false);
                    knx.led2=false;
                    pc.write(new GroupAddress(light3), false);
                    knx.led3=false;
                    pc.write(new GroupAddress(light4), false);
                    knx.led4=false;
                    Thread.sleep(knx.vitesse);

                    pc.write(new GroupAddress(light1), false);
                    knx.led1=false;
                    pc.write(new GroupAddress(light2), true);
                    knx.led2=true;
                    pc.write(new GroupAddress(light3), false);
                    knx.led3=false;
                    pc.write(new GroupAddress(light4), false);
                    knx.led4=false;
                    Thread.sleep(knx.vitesse);

                    pc.write(new GroupAddress(light1), false);
                    knx.led1=false;
                    pc.write(new GroupAddress(light2), false);
                    knx.led2=false;
                    pc.write(new GroupAddress(light3), true);
                    knx.led3=true;
                    pc.write(new GroupAddress(light4), false);
                    knx.led4=false;
                    Thread.sleep(knx.vitesse);

                    pc.write(new GroupAddress(light1), false);
                    knx.led1=false;
                    pc.write(new GroupAddress(light2), false);
                    knx.led2=false;
                    pc.write(new GroupAddress(light3), false);
                    knx.led3=false;
                    pc.write(new GroupAddress(light4), true);
                    knx.led4=true;
                    Thread.sleep(knx.vitesse);

                    pc.write(new GroupAddress(light1), false);
                    knx.led1=false;
                    pc.write(new GroupAddress(light2), false);
                    knx.led2=false;
                    pc.write(new GroupAddress(light3), true);
                    knx.led3=true;
                    pc.write(new GroupAddress(light4), false);
                    knx.led4=false;
                    Thread.sleep(knx.vitesse);

                    pc.write(new GroupAddress(light1), false);
                    knx.led1=false;
                    pc.write(new GroupAddress(light2), true);
                    knx.led2=true;
                    pc.write(new GroupAddress(light3), false);
                    knx.led3=false;
                    pc.write(new GroupAddress(light4), false);
                    knx.led4=false;
                    Thread.sleep(knx.vitesse);
                }
                else if (knx.motif==3){
                    pc.write(new GroupAddress(light1), true);
                    knx.led1=false;
                    pc.write(new GroupAddress(light2), true);
                    knx.led2=true;
                    pc.write(new GroupAddress(light3), true);
                    knx.led3=true;
                    pc.write(new GroupAddress(light4), true);
                    knx.led4=true;
                    Thread.sleep(knx.vitesse);

                    pc.write(new GroupAddress(light1), false);
                    knx.led1=false;
                    pc.write(new GroupAddress(light2), false);
                    knx.led2=false;
                    pc.write(new GroupAddress(light3), false);
                    knx.led3=false;
                    pc.write(new GroupAddress(light4), false);
                    knx.led4=false;
                    Thread.sleep(knx.vitesse);

                }

            }

        } catch (KNXException | InterruptedException e) {
            System.out.println("Error accessing KNX datapoint: " + e.getMessage());
        }

    }
}
