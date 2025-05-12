package com.mycompany.carbookingsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewBooking extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewBooking() {
        setTitle("View Bookings");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());

        // Table setup
        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load data
        loadBookingData();

        setVisible(true);
    }

    void loadBookingData() {
        try {
            DBHelper dbHelper = new DBHelper();
            Connection conn = dbHelper.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT booking_id, amount, return_date, booking_status,registration_number,licence_number, FROM booking");

            // Add column names
            model.addColumn("Booking ID");
            model.addColumn("Amount");
            model.addColumn("Return Date");
            model.addColumn("Booking Status");
          model.addColumn("Registration Number");
          model.addColumn("licence Number");

            // Add rows
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("booking_id"),
                        rs.getDouble("amount"),
                        rs.getString("return_date"),
                        rs.getString("booking_status"),
                  rs.getString("registration_number"),
                  rs.getString("licence_number")
                });
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading bookings: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ViewBooking();
    }
}
