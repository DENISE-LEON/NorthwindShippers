package com.pluralsight.models;

public class Shipper {
    int shipperID;
    String companyName;
    String phoneNumber;

    public Shipper(int shipperID, String companyName, String phoneNumber) {
        this.shipperID = shipperID;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public int getShipperID() {
        return shipperID;
    }

    public void setShipperID(int shipperID) {
        this.shipperID = shipperID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
