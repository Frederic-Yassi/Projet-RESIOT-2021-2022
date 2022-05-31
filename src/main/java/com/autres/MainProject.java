package com.autres;
import java.util.Scanner;

public class MainProject extends Thread{
    public static int vitesse;
    public static boolean sense ;
    public static boolean start ;

    public static void main(String[] args) {
        new MainProject().start();
    }

    @Override
    public void run() {

        while (true){
            Scanner myObj = new Scanner(System.in);
            //Scanner myObj2 = new Scanner(System.in);
            //String userName;

            // Enter username and press Enter
            System.out.println("Enter Vitesse");
            vitesse = myObj.nextInt();

            System.out.println("Enter sense");
            sense = myObj.nextBoolean();

            System.out.println("Enter Start");
            start = myObj.nextBoolean();

            System.out.println("vitesse is: " + vitesse);
            System.out.println("sense is: " + sense);
            System.out.println("Start is: " + start);

            new TextKnx().start();


            System.out.println("\n\n******************************\n\n");
        }

    }

}
