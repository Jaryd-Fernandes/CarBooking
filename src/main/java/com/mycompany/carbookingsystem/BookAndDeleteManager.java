
package com.mycompany.carbookingsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class BookAndDeleteManager extends JFrame implements ActionListener{
        JButton book,delete;
        String userEmail;
        BookAndDeleteManager(String userEmail){
        this.userEmail = userEmail;
            
        setSize(500, 300);
        setLocation(400,300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BookAndDeleteManager");
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
       
        JLabel text = new JLabel("Welcome To JK Car Rental ");
        text.setFont(new Font("Raleway",Font.BOLD,18));
        text.setBounds(110,25,250,25);
        add(text);
        
        JLabel l1 = new JLabel("Book a Car");
        l1.setFont(new Font("Raleway",Font.BOLD,14));
        l1.setBounds(160,70,250,25);
        add(l1);
        book = new JButton("Book");
        book.addActionListener(this);
        book.setBounds(160,90,140,40);
        add(book);
        
        JLabel l2 = new JLabel("Delete a Car");
        l2.setFont(new Font("System",Font.BOLD,14));
        l2.setBounds(160,160,250,25);
        add(l2);
        delete = new JButton("Delete");
        delete.setBounds(160,180,140,40);
        delete.addActionListener(this);
        add(delete);
        
        }
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == book){
                    setVisible(false);
                    new CarUI(userEmail).setVisible(true);
                }
                
                if(e.getSource() == delete){
                    setVisible(false);
                    new DeleteBookedCar(userEmail).setVisible(true);
                }
            }
        
        public static void main(String[] args){
            new BookAndDeleteManager("");
            
        }
}
