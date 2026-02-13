package com.healthcare.digital;

public class Admin extends User {

	String password;
    public Admin(String id, String name, String email,String password) {
        super(id, name, email);
        this.password = password;
    }

    public void addDoctor() {
        System.out.println("Admin " + getName() + " is adding a new doctor.");
        
    }

    public void removeDoctor() {
        System.out.println("Admin " + getName() + " is removing a doctor.");
        
    }

    @Override
    public void displayDashboard() {
        System.out.println("=== Admin Dashboard ===");
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
       
    }
}