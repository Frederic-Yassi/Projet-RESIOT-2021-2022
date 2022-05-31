package com.autres;

// Import package
import java.net.InetSocketAddress;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.KNXFormatException;
import tuwien.auto.calimero.KNXTimeoutException;
import tuwien.auto.calimero.link.KNXLinkClosedException;
import tuwien.auto.calimero.link.KNXNetworkLink;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;



public class ProcessCommunication{
    public static final String remoteHost = "192.168.0.200";
    public static final InetSocketAddress remote = new InetSocketAddress(remoteHost, 3671);
    //public static KNXNetworkLink knxLink = ;



    KNXNetworkLink knxLink = KNXNetworkLinkIP.newTunnelingLink(null, remote, false, new TPSettings());

    public final ProcessCommunicator pc = new ProcessCommunicatorImpl(knxLink);



    // Address of your KNXnet/IP server. Replace the IP host or address as necessary.

    private static final String lightState1 = "1/0/1";
    private static final String lightState2 = "1/0/2";
    private static final String lightState3 = "1/0/3";
    private static final String lightState4 = "1/0/4";


    // My Variable
    /*
    public static int vitesse = 0;
    public static boolean sense = false;
    public static boolean start = false;
    */


    public ProcessCommunication() throws KNXException, InterruptedException {
    }

    public static void main(final String[] args)
    {
        System.out.println("datapoint " + lightState4 + " value = " );
    }

      //  try  {
/*
            final boolean value1 = pc.readBool(new GroupAddress(lightState1));
            System.out.println("datapoint " + lightState1 + " value = " + value1);

            final boolean value2 = pc.readBool(new GroupAddress(lightState2));
            System.out.println("datapoint " + lightState2 + " value = " + value2);

            final boolean value3 = pc.readBool(new GroupAddress(lightState3));
            System.out.println("datapoint " + lightState3 + " value = " + value3);

            final boolean value4 = pc.readBool(new GroupAddress(lightState4));
            System.out.println("datapoint " + lightState4 + " value = " + value4);

            //setCheniar(true);

        }
        catch (KNXException | InterruptedException e) {
            System.out.println("Error accessing KNX datapoint: " + e.getMessage());
        }
        */
    //}

}



/*
    // We will read a boolean from the KNX datapoint with this light4address, replace the address as necessary.
    // Make sure this datapoint exists, otherwise you will get a read timeout!
    private static final String light1 = "0/0/1";
    private static final String light2 = "0/0/2";
    private static final String light3 = "0/0/3";
    private static final String light4= "0/0/4";
    */


//final InetSocketAddress remote = new InetSocketAddress(remoteHost, 3671);
// Create our network link, and pass it to a process communicator
/*(KNXNetworkLink knxLink = KNXNetworkLinkIP.newTunnelingLink(null, remote, false, new TPSettings()))*/ // juste en dessous

/*
            //pc = new ProcessCommunicatorImpl(knxLink);
            System.out.println("read boolean value from datapoint " + light4);
            final boolean value = pc.readBool(new GroupAddress(light4));
            System.out.println("datapoint " + light4+ " value = " + value);
            */


/*
    public static void setCheniar(boolean cheniar) throws KNXFormatException, KNXTimeoutException, KNXLinkClosedException, InterruptedException {
        System.out.println("setCheniar function");


        pc.write(new GroupAddress(light1), false);
        pc.write(new GroupAddress(light2), false);
        pc.write(new GroupAddress(light3), false);
        pc.write(new GroupAddress(light4), false);

        while (cheniar){
            if(sense == true){
                System.out.println(pc);
                pc.write(new GroupAddress(light1), true);
                pc.write(new GroupAddress(light2), false);
                pc.write(new GroupAddress(light3), false);
                pc.write(new GroupAddress(light4), false);
                Thread.sleep(vitesse);
                pc.write(new GroupAddress(light1), false);
                pc.write(new GroupAddress(light2), true);
                pc.write(new GroupAddress(light3), false);
                pc.write(new GroupAddress(light4), false);
                Thread.sleep(vitesse);
                pc.write(new GroupAddress(light1), false);
                pc.write(new GroupAddress(light2), false);
                pc.write(new GroupAddress(light3), true);
                pc.write(new GroupAddress(light4), false);
                Thread.sleep(vitesse);
                pc.write(new GroupAddress(light1), false);
                pc.write(new GroupAddress(light2),false );
                pc.write(new GroupAddress(light3), false);
                pc.write(new GroupAddress(light4), true);
                Thread.sleep(vitesse);
            }
            else {
                System.out.println(pc);
                pc.write(new GroupAddress(light1), false);
                pc.write(new GroupAddress(light2),false );
                pc.write(new GroupAddress(light3), false);
                pc.write(new GroupAddress(light4), true);
                Thread.sleep(vitesse);
                pc.write(new GroupAddress(light1), false);
                pc.write(new GroupAddress(light2), false);
                pc.write(new GroupAddress(light3), true);
                pc.write(new GroupAddress(light4), false);
                Thread.sleep(vitesse);
                pc.write(new GroupAddress(light1), false);
                pc.write(new GroupAddress(light2), true);
                pc.write(new GroupAddress(light3), false);
                pc.write(new GroupAddress(light4), false);
                Thread.sleep(vitesse);
                pc.write(new GroupAddress(light1), true);
                pc.write(new GroupAddress(light2), false);
                pc.write(new GroupAddress(light3), false);
                pc.write(new GroupAddress(light4), false);
                Thread.sleep(vitesse);
            }

        }


    }*/