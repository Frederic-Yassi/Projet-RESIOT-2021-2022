package com.Functions_knx;

import com.google.gson.Gson;

import java.util.ArrayList;

import static com.demo.ApirestApplication.knx;

public class KNX {
    boolean start;
    int pause ;
    int vitesse ;
    boolean sense;
    public boolean led1;
    public boolean led2;
    public boolean led3;
    public boolean led4;
    int motif ;

    public KNX() {
        start = false ;
        vitesse = 700 ;
        sense = false ; // 0 pour de la gauche vers la droite
        motif=0 ;
        pause=0;
        led1=false;
        led2=false;
        led3=false;
        led4=false;
    }

    public void start() {
        start=true ;
        //action
    }

    public void stop() {
        start = false;
        vitesse = 700 ;
        //action
    }

    public void changeSpeed(int v) {
        vitesse+=v;
        if (vitesse < 200 ) {
            vitesse = 200;
            System.out.println("Value of vitesse is" + vitesse);
        }
        if (vitesse > 700 ){
            vitesse=700;
            System.out.println("Value of vitesse is" + vitesse);
        }
    }

    public void changeSense() {
        sense = !sense ;
        //action
    }
    public void changeMotif() {
        motif++;
        if(motif>3){
            motif=0;
        }
        //a faire
    }

    public String Getstate() {
        String t ="";
        t+=led1==true ? "1" : "0" ;
        t+=led2==true ? "1" : "0" ;
        t+=led3==true ? "1" : "0" ;
        t+=led4==true ? "1" : "0" ;
        String gson = new Gson().toJson(t) ;
        return gson ;
    }
}