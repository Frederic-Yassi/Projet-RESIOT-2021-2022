package com.Functions_knx;

import java.net.InetSocketAddress;
import java.time.LocalTime;

import tuwien.auto.calimero.*;
import tuwien.auto.calimero.link.KNXNetworkLink;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;
import tuwien.auto.calimero.process.ProcessEvent;
import tuwien.auto.calimero.process.ProcessListener;

import static com.demo.ApirestApplication.knx;

/**
 * Example code showing how to use KNX process communication for group monitoring on a KNX Twisted Pair 1 (TP1) network.
 * On receiving group notifications, the KNX source and destination address are printed to System.out, as well as any
 * data part of the application service data unit (ASDU) in hexadecimal format.
 * <p>
 * Note that this example does not exit, i.e., it monitors forever or until the KNX network link connection got
 * closed. Hence, with KNX servers that have a limit on active tunneling connections (usually 1 or 4), if the group
 * monitor in connected state is terminated by the client (you), the pending state of the open tunnel on the KNX server
 * might temporarily cause an error on subsequent connection attempts.
 *
 * @author B. Malinowsky
 */
public class GroupMonitor implements ProcessListener {

    /**
     * Address of your KNXnet/IP server. Replace the host or IP address as necessary.
     */
    //public static boolean valeur = false;

    //private final KNXNetworkLink knxLink;

    //final String remoteHost;



    public static ProcessCommunicator pc;
    /*
    GroupMonitor(ProcessCommunicator pc ){
        this.pc = pc;
    }*/

    //static ProcessCommunicator pc;


    private static final String remoteHost = "192.168.0.201";

    /*public static void main(final String[] args) throws KNXException, InterruptedException {
        new GroupMonitor().run();
    }*/

    public void run() throws KNXException, InterruptedException {
        final InetSocketAddress remote = new InetSocketAddress(remoteHost, 3671);
        KNXNetworkLink knxLink = KNXNetworkLinkIP.newTunnelingLink(null, remote, false, new TPSettings());

        pc = new ProcessCommunicatorImpl(knxLink);
        pc.addProcessListener(this);

        System.out.println("mon  pc vaut" + pc);

        // start listening to group notifications using a process listener

        System.out.println("Monitoring KNX network using KNXnet/IP server " + remoteHost + " ...");

        while (knxLink.isOpen()) Thread.sleep(1000);

    }

    //if(e.getDestination())

    @Override
    public void groupWrite(final ProcessEvent e) {

            System.out.println("group write function");

        print("write.ind", e);
    }
    @Override
    public void groupReadRequest(final ProcessEvent e) { print("read.req", e); }
    @Override
    public void groupReadResponse(final ProcessEvent e) { print("read.res", e); }
    @Override
    public void detached(final DetachEvent e) {}

    // Called on every group notification issued by a datapoint on the KNX network. It prints the service primitive,
    // KNX source and destination address, and Application Service Data Unit (ASDU) to System.out.
    private static void print(final String svc, final ProcessEvent e) {
        try {

            System.out.println(LocalTime.now() + " " + e.getSourceAddr() + "->" + e.getDestination() + " " + svc
                    + ": " + DataUnitBuilder.toHex(e.getASDU(), "") );

            if(e.getDestination().toString().equals( "1/0/1")){
                if (DataUnitBuilder.toHex(e.getASDU(), "").toString().equals("01")){
                    if (!knx.start){
                        knx.start  = true;
                        new MultithreadThing(pc).start();

                    } else {
                        knx.start = false;
                    }
                    System.out.println("Value of start is "+ knx.start);
                    //new MultithreadThing(pc).start();
                }
            } else if (e.getDestination().toString().equals( "1/0/2")){
                if (DataUnitBuilder.toHex(e.getASDU(), "").toString().equals("01")){
                    if (!knx.sense){
                        knx.sense  = true;
                        System.out.println("Changement du sens à " + knx.sense);
                    } else {
                        knx.sense = false;
                        System.out.println("Changement du sens à " + knx.sense);
                    }
                    System.out.println(knx.sense);
                }

            }else if (e.getDestination().toString().equals( "1/0/3")){
                if (DataUnitBuilder.toHex(e.getASDU(), "").toString().equals("01")){
                    if (knx.vitesse > 200 && knx.vitesse <=700){
                        knx.vitesse -= 100;
                        System.out.println("Value of vitesse is" + knx.vitesse);
                        //new MultithreadThing(pc).start();

                    }else {
                        knx.vitesse=700;
                        //new MultithreadThing(pc).start();
                        System.out.println("Value of vitesse is" + knx.vitesse);
                    }
                }


            }else if (e.getDestination().toString().equals( "1/0/4")){
                if (DataUnitBuilder.toHex(e.getASDU(), "").toString().equals("01")){
                    //cheniar aleatoire
                    if (knx.motif >= 0 && knx.motif <=2){
                        knx.motif += 1;
                        //new MultithreadThing(pc).start();
                    }else {
                        knx.motif=0;
                    }

                    //accelerer
                    /*
                    if (MultithreadThing.vitesse<=2000){
                        MultithreadThing.vitesse += 400;
                        //new MultithreadThing(pc).start();
                    }else {
                        MultithreadThing.vitesse=2000;
                    }*/
                }

            }

            // Soit bouton 3 et 4 pour regler la vitesse
            // ou bouton 3 vitesse et bouton 4 motif
        }
        catch (final RuntimeException ex) {
            System.err.println(ex);
        }
    }
}