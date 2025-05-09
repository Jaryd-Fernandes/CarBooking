package com.mycompany.carbookingsystem;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener {
    JButton customerBtn,carCatBtn,carBtn;
    
    public MainMenu() {
        setTitle("Car Booking Management System");
        setSize(450, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JLabel label = new JLabel("Admin Only");
        label.setBounds(100, 20, 80, 25);
        add(label);
        
        customerBtn = new JButton("Customer Login/Register");
        customerBtn.setBounds(120,50,200,50);
        customerBtn.addActionListener(this);
        
        
        carCatBtn = new JButton("Manage Car Categories");
        carCatBtn.setBounds(120,100,200,50);
        carCatBtn.addActionListener(this);
        
        carBtn = new JButton("Manage Cars");
        carBtn.setBounds(120,150,200,50);
        carBtn.addActionListener(this);
        
        add(customerBtn); 
        add(carCatBtn); 
        add(carBtn);
        
    }
    
     public void actionPerformed(ActionEvent e){
             if(e.getSource() == customerBtn){
             setVisible(false);
             new CustomerLoginRegister().setVisible(true);
             
            }
             if(e.getSource() ==  carCatBtn){
             setVisible(false);
             new CarCategoryManager().setVisible(true);
             
            }
             if(e.getSource() == carBtn){
             setVisible(false);
             new CarManager().setVisible(true);
             
            }
             
             
             
             
             
     }
     
     
             
    public static void main(String[] args) { 
        new MainMenu(); 
    }

}