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
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(500,100);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);

        JLabel l1 = new JLabel("Email: ");
        l1.setBounds( 40, 30, 150, 25);
        add(l1);
        emailField = new JTextField();
        emailField.setBounds( 150, 30, 200, 25);
        add(emailField);
        
        JLabel l2 = new JLabel("Password:"); 
        l2.setBounds(40,90,150,25);
        add(l2);
        passwordField = new JTextField();
        passwordField.setBounds( 150, 90, 200, 25);
        add(passwordField);
        
        JLabel l3 = new JLabel("Name:");
        l3.setBounds(40,150,150,25);
        add(l3);
        nameField = new JTextField(); 
        nameField.setBounds(150,150,200,25);
        add(nameField);
        
        JLabel l4 = new JLabel("Phone:");
        l4.setBounds(40,210,150,25);
        add(l4); 
        phoneField = new JTextField(); 
        phoneField.setBounds(150,210,200,25);
        add(phoneField);
        
        JLabel l5 = new JLabel("Licence No:");
        l5.setBounds(40,270,150,25);
        add(l5); 
        licenceField = new JTextField(); 
        licenceField.setBounds(150,270,200,25);
        add(licenceField);

        loginBtn = new JButton("Login"); 
        loginBtn.setBounds(70,330,100,30);
        registerBtn = new JButton("Register");
        registerBtn.setBounds(190,330,100,30);
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
            DBCon dbHelper = new DBCon(); 
            Connection conn = dbHelper.getConnection(); 
            
            
            String sql = "SELECT * FROM customer WHERE email_id=? AND driving_licence_number=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emailField.getText());
            ps.setString(2, licenceField.getText());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                new BookAndDeleteManager(emailField.getText());
                //new CarUI(costField.getText());
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
            
            //rs.close();
            //ps.close();
            //conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    void register() {
        try {
             DBCon dbHelper = new DBCon(); 
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
