
package com.mycompany.carbookingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CarUI extends JFrame {
    JComboBox<CarItem> carBox;
    JTextField returnDateField;
    JButton bookBtn;
    Connection conn;
    String userEmail;
    //double Cost;
    
    class CarItem {
    String registrationNumber;
    String model;

    CarItem(String registrationNumber, String model) {
        this.registrationNumber = registrationNumber;
        this.model = model;
    }

    @Override
    public String toString() {
        return model; 
    }
}


    public CarUI(String userEmail) {   //double Cost
        this.userEmail = userEmail;
        
        setSize(500, 300);
        setLocation(500,200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Book a Car");
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);

        carBox = new JComboBox<>();
        carBox.setBounds(210,30,200,25);
        add(carBox);
        
        JLabel sc = new JLabel("Select Car:");
        sc.setBounds(40, 30, 150, 25);
        add(sc);
        
        JLabel l2 = new JLabel("Return Date (YYYY-MM-DD):");
        l2.setBounds(40, 90, 190, 30);
        add(l2);
        
        returnDateField = new JTextField();
        returnDateField.setBounds(210, 90, 200, 30);
        add(returnDateField);
        
        bookBtn = new JButton("Book");
        bookBtn.setBounds(200,190,90,30);
        bookBtn.addActionListener(e -> bookCar());
        add(bookBtn);
        
      
        

        setVisible(true);
        loadAvailableCars();
    }

    
    void loadAvailableCars() {
         try {
        DBCon dbHelper = new DBCon(); // create DBCon instance
        Connection conn = dbHelper.getConnection();

        String sql = "SELECT registration_number, model FROM car WHERE availability='Available'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        //ResultSet rs1 = stmt.executeQuery(sql);

        while (rs.next()) {
            String reg = rs.getString("registration_number");
            String model = rs.getString("model");
            carBox.addItem(new CarItem(reg, model));        
        }

        rs.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }


    void bookCar() {
        try {
            DBCon dbHelper = new DBCon(); 
            Connection conn = dbHelper.getConnection();
            CarItem selectedCar = (CarItem) carBox.getSelectedItem();
            String regNo = selectedCar.registrationNumber;
            String returnDate = returnDateField.getText();

            // Get licence number from customer email
            String getLicenceSQL = "SELECT driving_licence_number FROM customer WHERE email_id=?";
            PreparedStatement ps1 = conn.prepareStatement(getLicenceSQL);
            //PreparedStatement ps2 = conn.prepareStatement(getLicenceSQL);
            ps1.setString(1, userEmail);
            //ps2.setDouble(2, Cost);
            
            ResultSet rs = ps1.executeQuery();
            //ResultSet rs1 = ps2.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "User not found.");
                return;
            }
            String licence = rs.getString("driving_licence_number");
            //Public String costField;
            
            //amount issus fix changes
            
            String costSql = "SELECT cc.cost_per_day " +
                 "FROM car c JOIN car_category cc ON c.category_id = cc.category_id " +
                 "WHERE c.registration_number = ?";
            PreparedStatement costStmt = conn.prepareStatement(costSql);
            costStmt.setString(1, regNo);
            ResultSet costRs = costStmt.executeQuery();

            double costPerDay = 0;
                if (costRs.next()) {
                costPerDay = costRs.getDouble("cost_per_day");
            } else 
                {
            JOptionPane.showMessageDialog(this, "Cost per day not found for selected car.");
                    return;
                }
          
           String insertSQL = "INSERT INTO booking (booking_status, return_date,registration_number, licence_number,amount) VALUES ('Booked', ?, ?, ?,?)";       //amount?
            PreparedStatement ps4 = conn.prepareStatement(insertSQL);
            ps4.setString(1, returnDate);        // should be index 1 -> return_date
            ps4.setString(2, regNo);             // should be index 3 -> registration_number
            ps4.setString(3, licence);           // should be index 4 -> licence_number
            ps4.setDouble(4, costPerDay);              // should be index 4 -> amount
            ps4.executeUpdate();

            // Update car availability
            String updateSQL = "UPDATE car SET availability='Booked' WHERE registration_number=?";
            PreparedStatement ps3 = conn.prepareStatement(updateSQL);
            ps3.setString(1, regNo);
            ps3.executeUpdate();

            JOptionPane.showMessageDialog(this, "Car booked successfully!");
            
            setVisible(false);
            new MainMenu().setVisible(true);
            
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Booking failed: " + e.getMessage());
        }
    }
     


    public static void main(String[] args){
        new CarUI("");     //0.0
    }
}

