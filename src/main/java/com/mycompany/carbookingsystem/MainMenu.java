
package com.mycompany.carbookingsystem;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener {
    JButton customerBtn, carCatBtn, carBtn, viewBookingBtn;  // Step 1: Add new button

    public MainMenu() {
        setTitle("Car Booking Management System");
        setSize(450, 350); // increased height to fit the new button
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        

        JLabel label = new JLabel("Admin Only");
        label.setBounds(100, 20, 100, 25);
        add(label);

        customerBtn = new JButton("Customer Login/Register");
        customerBtn.setBounds(120, 50, 200, 40);
        customerBtn.addActionListener(this);

        carCatBtn = new JButton("Manage Car Categories");
        carCatBtn.setBounds(120, 100, 200, 40);
        carCatBtn.addActionListener(this);

        carBtn = new JButton("Manage Cars");
        carBtn.setBounds(120, 150, 200, 40);
        carBtn.addActionListener(this);

        viewBookingBtn = new JButton("View Bookings"); 
        viewBookingBtn.setBounds(120, 200, 200, 40);    
        viewBookingBtn.addActionListener(this);         
       
        add(customerBtn);
        add(carCatBtn);
        add(carBtn);
        add(viewBookingBtn);
        
         getContentPane().setBackground(Color.BLACK);
        label.setForeground(Color.RED);
        //customerBtn.setForeground(Color.WHITE);
        //customerBtn.setBackground(Color.BLUE);
        //carCatBtn.setForeground(Color.WHITE);
       // carCatBtn.setBackground(Color.GREEN);
        //carBtn.setForeground(Color.WHITE);
        //viewBookingBtn.setForeground(Color.BLACK);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customerBtn) {
            setVisible(false);
            new CustomerLoginRegister().setVisible(true);
        } else if (e.getSource() == carCatBtn) {
            setVisible(false);
            new CarCategoryManager().setVisible(true);
        } else if (e.getSource() == carBtn) {
            setVisible(false);
            new CarManager().setVisible(true);
        } else if (e.getSource() == viewBookingBtn) {
            new ViewBooking().setVisible(true); 
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
