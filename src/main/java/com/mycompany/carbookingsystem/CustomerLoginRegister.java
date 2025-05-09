package com.mycompany.carbookingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerLoginRegister extends JFrame {
    JTextField emailField, passwordField, nameField, phoneField, licenceField;
    JButton loginBtn, registerBtn;
    Connection conn;

    public CustomerLoginRegister() {
        setTitle("Customer Login/Register");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
        setLocationRelativeTo(null);

        add(new JLabel("Email:")); emailField = new JTextField(); add(emailField);
        add(new JLabel("Password:")); passwordField = new JTextField(); add(passwordField);
        add(new JLabel("Name:")); nameField = new JTextField(); add(nameField);
        add(new JLabel("Phone:")); phoneField = new JTextField(); add(phoneField);
        add(new JLabel("Licence No:")); licenceField = new JTextField(); add(licenceField);

        loginBtn = new JButton("Login"); 
        registerBtn = new JButton("Register");
        add(loginBtn); add(registerBtn);

        loginBtn.addActionListener(e -> login());
        registerBtn.addActionListener(e -> register());

        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "DB Error: " + ex.getMessage());
        }

        setVisible(true);
    }

    void login() {
        try {
            DBHelper dbHelper = new DBHelper(); // create DBHelper instance
            Connection conn = dbHelper.getConnection(); // get the actual DB connection
            
            
            String sql = "SELECT * FROM customer WHERE email_id=? AND driving_licence_number=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emailField.getText());
            ps.setString(2, licenceField.getText());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                new BookAndDeleteManager(emailField.getText());
                //new CarUI(costField.getText());
                //dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
            setVisible(false);
            //rs.close();
            //ps.close();
            //conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    void register() {
        try {
             DBHelper dbHelper = new DBHelper(); // create DBHelper instance
            Connection conn = dbHelper.getConnection();
            String sql = "INSERT INTO customer (email_id, first_name, phone_number, driving_licence_number) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emailField.getText());
            ps.setString(2, nameField.getText());
            ps.setString(3, phoneField.getText());
            ps.setString(4, licenceField.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration successful!");
            
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Registration error: " + ex.getMessage());
        }
    }
    public static void main(String[] args){
        new CustomerLoginRegister();
}
    
}
