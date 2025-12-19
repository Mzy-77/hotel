package com.mycompany.hotel;

public class Customers extends User {

    private int passID;

    public Customers(int passID, String name, String phone, String email) {
        super(passID, name, phone, email);   // use passID as User ID
        this.passID = passID;
    }

    public int getPassID() {
        return passID;
    }
}


