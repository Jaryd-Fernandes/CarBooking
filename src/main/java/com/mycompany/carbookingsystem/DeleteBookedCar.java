package com.mycompany.carbookingsystem;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import com.mycompany.carbookingsystem.CarUI.CarItem;


public class DeleteBookedCar  extends JFrame{
    String licence;
    String userEmail;
    JComboBox<CarItem> carBox = new JComboBox<>();

    JButton book,delete;
    JTextField returnDateField;
   
    class CarItem {
    String registrationNumber;
    String model;

    CarItem(String registrationNumber, String model) {
        this.registrationNumber = registrationNumber;
        this.model = model;
    }

    @Override
    public String toString() {
        return model; 
    }
}
    
    
    DeleteBookedCar(String userEmail) {
        this.userEmail = userEmail;
        
        setSize(500, 300);
        setLocation(500,200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete Booked car"); 
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
        //
        carBox = new JComboBox<>();
        carBox.setBounds(130, 65, 150, 35);  // Set position and size
        add(carBox);
        
        JLabel text = new JLabel("Select Car to Cancel Booking: ");
        text.setFont(new Font("System",Font.BOLD,15));
        text.setBounds(110,30,250,25);
        add(text);
        
        delete = new JButton("Delete");
        delete.setBounds(170,150,140,40);
        add(delete);
        
        delete.addActionListener(e -> deleteCar());
        loadBookedCars();
    }
    
    void loadBookedCars() {
         try {
        DBCon dbHelper = new DBCon(); // create DBCon instance
        Connection conn = dbHelper.getConnection();
        
        /*String query = "SELECT * FROM customer ";
        Statement stemt = conn.createStatement();
        ResultSet rx = stemt.executeQuery(query);
        while (rx.next()) {
            licence = rx.getString("driving_licence_number");
                  
        }
        
        

        String sql = "SELECT c.registration_number, c.model FROM car c JOIN booking b ON c.registration_number = b.registration_number WHERE c.availability = 'Booked' AND b.licence_number = '"+licence+"' ";
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String reg = rs.getString("registration_number");
            String model = rs.getString("model");
            carBox.addItem(new CarItem(reg, model));        
        }

        rs.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }*/
  
        // Step 1: Get licence number for the given user email
        String licQuery = "SELECT driving_licence_number FROM customer WHERE email_id = ?";
        PreparedStatement pLic = conn.prepareStatement(licQuery);
        pLic.setString(1, userEmail);
        ResultSet licRs = pLic.executeQuery();

        if (licRs.next()) {
            licence = licRs.getString("driving_licence_number");
        } else {
            JOptionPane.showMessageDialog(this, "No customer found with this email.");
            return;
        }

        // Step 2: Get booked cars using the licence number
        String sql = "SELECT c.registration_number, c.model " +
                     "FROM car c " +
                     "JOIN booking b ON c.registration_number = b.registration_number " +
                     "WHERE c.availability = 'Booked' AND b.licence_number = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, licence);
        ResultSet rs = ps.executeQuery();

        boolean found = false;
        while (rs.next()) {
            String reg = rs.getString("registration_number");
            String model = rs.getString("model");
            System.out.println("DEBUG: Found booked car - " + reg + " (" + model + ")");
            carBox.addItem(new CarItem(reg, model));
            found = true;
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "No booked cars found for this user.");
        }

        rs.close();
        ps.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading booked cars: " + e.getMessage());
    }
}


    
    void deleteCar(){
        try{
            DBCon dbHelper = new DBCon(); 
            Connection conn = dbHelper.getConnection();
           CarItem selectedCar = (CarItem) carBox.getSelectedItem();

            String regNo = selectedCar.registrationNumber;
            
            String updateSQL = "UPDATE car SET availability='Available' WHERE registration_number=?";
            PreparedStatement ps3 = conn.prepareStatement(updateSQL);
            ps3.setString(1, regNo);
            ps3.executeUpdate();

            JOptionPane.showMessageDialog(this, "Car booking canceled successfully!");
            
            setVisible(false);
            new MainMenu().setVisible(true);
            conn.close();
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    public static void main(String[] args){
        new DeleteBookedCar("");
    }
    
}
