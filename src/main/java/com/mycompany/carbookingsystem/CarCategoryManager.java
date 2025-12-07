package com.mycompany.carbookingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.PreparedStatement;

public class CarCategoryManager extends JFrame implements ActionListener {
    JTextField nameField, personField, costField, feesField,categoryField ;
    JButton addBtn;
    Connection conn;

    public CarCategoryManager() {
        setTitle("Manage Car Categories");

        setSize(600, 400);
        setLayout(new GridLayout(6, 2));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        categoryField = new JTextField();
        nameField = new JTextField();
        personField = new JTextField();
        costField = new JTextField();
        feesField = new JTextField();
        
        
        add(new JLabel("Category Name:")); add(categoryField);
        add(new JLabel("Name of thr car:")); add(nameField);
        add(new JLabel("Number of Persons:")); add(personField);
        add(new JLabel("Cost per Day:")); add(costField);
        add(new JLabel("Late Fees:")); add(feesField);

        addBtn = new JButton("Add Category"); add(addBtn);
        addBtn.addActionListener(this);

      

        setVisible(true);
    }
public void actionPerformed(ActionEvent e){
    if(e.getSource() == addBtn){
        String category=categoryField.getText();
        String name=nameField.getText();
        int persons=Integer.parseInt(personField.getText());
        double cost=Double.parseDouble(costField.getText());
         double fees=Double.parseDouble(feesField.getText());
        
        
         try {
            DBCon db = new DBCon();
            Connection conn = db.getConnection();
            String sql = "INSERT INTO car_category (category_id,name, number_of_person, cost_per_day, late_fees) VALUES (?,?, ?, ?, ?)";
           // Connection connection = conn.connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1 ,category);
            ps.setString(2 ,name);
            ps.setInt(3, persons);
            ps.setDouble(4,cost);
            ps.setDouble(5, fees);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Category added.");
           // CarBookingUI obj=new CarBookinUI(costField);
        } catch (SQLException ae) {
            ae.printStackTrace();
        }
         setVisible(false);
         new MainMenu().setVisible(true);
     }

 }

public static void main(String[] args){
    new CarCategoryManager();
}
   
}
