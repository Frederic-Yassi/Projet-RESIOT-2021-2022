package com.autres;

/**
 * Hello world!
 *
 */
public class App 
{
    private static int vitesse = 1000;
    public static void main( String[] args ) throws InterruptedException {
      t(20);
      System.out.println(vitesse);
    }
    static void t(int t){
        vitesse = t;
    }
}
