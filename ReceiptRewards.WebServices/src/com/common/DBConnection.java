package com.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "cscsql2.carrollu.edu";
    private static final String USER = "abottom2";
    private static final String PASSWORD = "340844";
    private static final String PORT = "";
    private static final String DATABASE = "abottom2";
    
    public static Connection getConnection() {
        // Instantiate Variables
        String connectionURL = "";
        Connection con = null;
        
        try { // Try to create connection
         connectionURL = "jdbc:sqlserver://" + DBConnection.URL + ";DatabaseName=" + DBConnection.DATABASE + ";user=" + DBConnection.USER + ";Password=" + DBConnection.PASSWORD;
         con = DriverManager.getConnection(connectionURL);
        } catch(Exception ex) {
            System.out.println("SQL Connection Error");
            // @TODO: Handle Exception
        }
        return con;
    }
    
}
