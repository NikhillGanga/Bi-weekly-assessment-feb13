package com.healthcare.digital;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User {
    private List<MedicalRecord> medicalHistory;
    private List<Prescription> prescriptions;

    public Patient(String id, String name, String email) {
        super(id, name, email);
        this.medicalHistory = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public List<MedicalRecord> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(List<MedicalRecord> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public void addMedicalRecord(MedicalRecord record) {
        this.medicalHistory.add(record);
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
    }

   

    public void viewPrescriptions() {
        System.out.println("Prescriptions for patient: " + getName());
        for (Prescription prescription : prescriptions) {
            System.out.println(prescription);
        }
    }
    

    @Override
	public String toString() {
		return "Patient [medicalHistory=" + medicalHistory + ", prescriptions=" + prescriptions + ", Id" + getId()
				+ ", Name=" + getName() + ", Email=" + getEmail() + "]";
	}

	@Override
    public void displayDashboard() {
        System.out.println("=== Patient Dashboard ===");
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Medical Records: " + medicalHistory.size());
        System.out.println("Prescriptions: " + prescriptions.size());
    }
}