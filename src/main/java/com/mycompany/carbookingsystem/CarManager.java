package com.mycompany.carbookingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CarManager extends JFrame implements ActionListener {
    JTextField makeField, modelField, yearField, mileageField, regField;
    JComboBox<String> categoryIdBox;
    JButton addBtn;
    Connection conn;

    public CarManager() {
        setTitle("Manage Cars");
        setSize(400, 500);
        setLocation(500,200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.PINK);
        setVisible(true);


        JLabel l1 = new JLabel("Reg No:");
        l1.setBounds(30,30,150,25);
        add(l1);
        regField = new JTextField(); 
        regField.setBounds(150,30,200,25);
        add(regField);
        
        JLabel l2 = new JLabel("Make:");
        l2.setBounds(30,90,150,25);
        add(l2);  
        makeField = new JTextField();
        makeField.setBounds(150,90,200,25);
        add(makeField);
        
        JLabel l3 = new JLabel("Model:");
        l3.setBounds(30,150,150,25);
        add(l3); 
        modelField = new JTextField();
        modelField.setBounds(150,150,200,25);
        add(modelField);
        
        JLabel l4 = new JLabel("Year:");
        l4.setBounds(30,210,150,25);
        add(l4); 
        yearField = new JTextField();
        yearField.setBounds(150,210,200,25);
        add(yearField);
        
        JLabel l5 = new JLabel("Mileage:");
        l5.setBounds(30,270,150,25);
        add(l5); 
        mileageField = new JTextField();
        mileageField.setBounds(150,270,200,25);
        add(mileageField);
        
        JLabel l6 = new JLabel("Category ID:");
        l6.setBounds(30,330,150,25);
        add(l6);
        categoryIdBox = new JComboBox<>(new String[]{"SEDAN", "SUV", "HATCHBACK"});
        categoryIdBox.setBounds(150,330,200,25);
        add(categoryIdBox);

        addBtn = new JButton("Add Car"); 
        addBtn.setBounds(200,380,90,30);
        add(addBtn);
        addBtn.addActionListener(this);
        setVisible(true);
    }
        public void actionPerformed(ActionEvent ae){
    if(ae.getSource() == addBtn){
        String registration_number=regField.getText();
        String make=makeField.getText();
        String model=modelField.getText();
        int modelyear=Integer.parseInt(yearField.getText());
        double milage=Double.parseDouble(mileageField.getText());
        String category=(String) categoryIdBox.getSelectedItem();

        
        

        
        try {
            DBCon db = new DBCon();
            Connection conn = db.getConnection();
            System.out.println("DEBUG:connection="+conn);
            String sql = "INSERT INTO car (make, model, model_year, mileage, registration_number, availability, category_id) VALUES (?, ?, ?, ?, ?, 'Available', ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, make);
            ps.setString(2, model);
            ps.setInt(3, modelyear);
            ps.setDouble(4, milage);
            ps.setString(5, registration_number);
            
            ps.setString(6,category );
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Car added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
         setVisible(false);
         new MainMenu().setVisible(true);
        
    }
  }
        
        public static void main(String[] args){
    new CarManager();
}
}

        


