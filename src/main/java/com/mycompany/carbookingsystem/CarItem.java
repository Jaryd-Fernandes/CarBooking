package com.mycompany.carbookingsystem;



public class CarItem {
    public String registrationNumber;
    public String model;

    public CarItem(String registrationNumber, String model) {
        this.registrationNumber = registrationNumber;
        this.model = model;
    }

    @Override
    public String toString() {
        return model != null ? model : registrationNumber;
    }
  public static void main(String[] args) {
        CarItem car = new CarItem("", "");
    }
}

