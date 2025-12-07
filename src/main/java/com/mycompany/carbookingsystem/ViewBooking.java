package com.mycompany.carbookingsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewBooking extends JFrame {

    JTable bookingTable, carTable;
    DefaultTableModel bookingModel, carModel;

    public ViewBooking() {
        setTitle("View Bookings and Cars");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Booking table
        bookingModel = new DefaultTableModel();
        bookingTable = new JTable(bookingModel);
        JScrollPane bookingScrollPane = new JScrollPane(bookingTable);
        bookingScrollPane.setBorder(BorderFactory.createTitledBorder("Bookings"));
        add(bookingScrollPane, BorderLayout.NORTH);

        // Car table
        carModel = new DefaultTableModel();
        carTable = new JTable(carModel);
        JScrollPane carScrollPane = new JScrollPane(carTable);
        carScrollPane.setBorder(BorderFactory.createTitledBorder("Cars"));
        add(carScrollPane, BorderLayout.CENTER);

        loadBookingData();
        loadCarData();

        setVisible(true);
    }

    void loadBookingData() {
        try {
            DBCon dbHelper = new DBCon();
            Connection conn = dbHelper.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT booking_id, amount, return_date, booking_status, registration_number, licence_number FROM booking");

            bookingModel.setColumnIdentifiers(new String[]{"Booking ID", "Amount", "Return Date", "Booking Status", "Registration Number", "Licence Number"});

            while (rs.next()) {
                bookingModel.addRow(new Object[]{
                        rs.getInt("booking_id"),
                        rs.getDouble("amount"),
                        rs.getString("return_date"),
                        rs.getString("booking_status"),
                        rs.getString("registration_number"),
                        rs.getString("licence_number")
                });
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading bookings: " + e.getMessage());
        }
    }

    void loadCarData() {
        try {
            DBCon dbHelper = new DBCon();
            Connection conn = dbHelper.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT registration_number, make, model, model_year, mileage, availability FROM car");

            carModel.setColumnIdentifiers(new String[]{"Registration Number", "Make", "Model", "Year", "Mileage", "Available"});

            while (rs.next()) {
                String availability = rs.getString("availability");
                carModel.addRow(new Object[]{
                        rs.getString("registration_number"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("model_year"),
                        rs.getInt("mileage"),
                        availability
                });
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading cars: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ViewBooking();
    }
}
