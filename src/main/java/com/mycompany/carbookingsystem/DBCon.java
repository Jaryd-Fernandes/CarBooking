package com.mycompany.carbookingsystem;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
    private static final String URL = "jdbc:mysql://localhost:3306/car_rental"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "admin"; 

    public Connection getConnection() {
         try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connection successful: " + conn);
        return conn;
    } catch (SQLException se) {
        System.err.println("SQL Exception: " + se.getMessage());
        se.printStackTrace();
        return null;
    } catch (ClassNotFoundException ce) {
        System.err.println("JDBC Driver not found: " + ce.getMessage());
        ce.printStackTrace();
        return null;
    }
}
    public static void main(String[] args) {
        new DBCon();
    }
}

