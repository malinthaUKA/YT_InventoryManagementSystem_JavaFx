/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yt_inventorymanagementsystem_javafx;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author USER
 */
public class databaseHandler {
    
    public static Connection connectDb(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "@12n");
            
            return con;
            
        } catch (Exception e) {e.printStackTrace();}
        
        return null;
    }
}
