package com.Functions_bdd;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.mysql.jdbc.Connection;

public class db {
    static Connection  con = null;
    public static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/historique_knx?characterEncoding=utf8", "root","root");

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Erreur de connection");
        }
        System.out.println("connection base de données ok");
    }
    public static void deconnect(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void add(String commande,String details) {
        Statement s;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("INSERT INTO info (date,commande,details) values('%1$s','%2$s','%3$s')",timeStamp,commande,details);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void get() {
        Statement s;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql = "select * from info ";
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql); 

                // on parcourt l'ensemble des résultats retourné par la requête
            while (resultSet.next()) {
                String titre = resultSet.getString("");
                java.sql.Date dateSortie = resultSet.getDate("date");
                long duree = resultSet.getLong("duree");

                // ...
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
