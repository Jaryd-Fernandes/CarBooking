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
        setLayout(new GridLayout(7, 2));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        makeField = new JTextField(); modelField = new JTextField();
        yearField = new JTextField(); mileageField = new JTextField();
        regField = new JTextField();
        categoryIdBox = new JComboBox<>(new String[]{"SEDAN", "SUV", "HATCHBACK"});

        add(new JLabel("Reg No:")); add(regField);
        add(new JLabel("Make:")); add(makeField);
        add(new JLabel("Model:")); add(modelField);
        add(new JLabel("Year:")); add(yearField);
        add(new JLabel("Mileage:")); add(mileageField);
        add(new JLabel("Category ID:")); add(categoryIdBox);

        addBtn = new JButton("Add Car"); add(addBtn);
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
            DBHelper db = new DBHelper();
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

        


