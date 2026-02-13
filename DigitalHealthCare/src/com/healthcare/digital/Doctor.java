package com.healthcare.digital;

public class Doctor extends User {
    private String specialization;
    private int experience;

    public Doctor(String id, String name, String email, String specialization, int experience) {
        super(id, name, email);
        this.specialization = specialization;
        this.experience = experience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void addPrescription() {
        System.out.println("Doctor " + getName() + " is adding a prescription.");
       
    }

    public void viewAppointments() {
        System.out.println("Viewing appointments for doctor: " + getName());
        
    }

    

	@Override
	public String toString() {
		return "Doctor [specialization=" + specialization + ", experience=" + experience + ", Id=" + getId()
				+ ", Name=" + getName() + ", Email=" + getEmail() + "]";
	}

	
	@Override
    public void displayDashboard() {
        System.out.println("=== Doctor Dashboard ===");
       toString();
    }
}