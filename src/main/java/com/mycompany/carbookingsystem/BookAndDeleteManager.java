
package com.mycompany.carbookingsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import javax.imageio.ImageIO;


public class BookAndDeleteManager extends JFrame implements ActionListener{
        JButton book,delete;
        String userEmail;
        private Image backgroundImage;
         class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
        BookAndDeleteManager(String userEmail){
        this.userEmail = userEmail;
         
        try {
            
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/images/booking_background.jpg"));
        } catch (IOException e) {
            System.err.println("Error loading background image: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        
        setSize(500, 300);
        setLocation(400,300);
       setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("BookAndDeleteManager");
        
        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(null);
        setContentPane(panel);
       //getContentPane().setBackground(Color.WHITE);
       
        
        JLabel text = new JLabel("Welcome To JK Rental Admin");
        text.setFont(new Font("Raleway",Font.BOLD,18));
        text.setBounds(110,25,290,35);
        text.setForeground(Color.BLACK);
        panel.add(text);
        //add(text);
        
        JLabel l1 = new JLabel("Book a Car");
        l1.setFont(new Font("Raleway",Font.BOLD,14));
        l1.setBounds(160,70,250,25);
        add(l1);
        l1.setForeground(Color.GREEN);
        book = new JButton("Book");
        book.addActionListener(this);
        book.setBounds(160,90,140,40);
        add(book);
        book.setForeground(Color.GREEN);
        
        JLabel l2 = new JLabel("Delete a Car");
        l2.setFont(new Font("Raleway",Font.BOLD,14));
        l2.setBounds(160,160,250,25);
        add(l2);
        l2.setForeground(Color.RED);
        delete = new JButton("Delete");
        delete.setBounds(160,180,140,40);
        delete.addActionListener(this);
        add(delete);
        delete.setForeground(Color.RED);
        setVisible(true);

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

