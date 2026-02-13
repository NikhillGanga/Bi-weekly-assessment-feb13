package com.healthcare.digital;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private String prescriptionId;
    private List<String> medicines;
    private LocalDate prescribedDate;

    public Prescription(String prescriptionId, LocalDate prescribedDate) {
        this.prescriptionId = prescriptionId;
        this.prescribedDate = prescribedDate;
        this.medicines = new ArrayList<>();
    }

    public Prescription(String string, LocalDate of, Object object) {
		this.prescriptionId = string;
		this.prescribedDate = of;
		this.medicines = new ArrayList<>();
		}

	

	public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public List<String> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<String> medicines) {
        this.medicines = medicines;
    }

    public void addMedicine(String medicine) {
        this.medicines.add(medicine);
    }

    public LocalDate getPrescribedDate() {
        return prescribedDate;
    }

    public void setPrescribedDate(LocalDate prescribedDate) {
        this.prescribedDate = prescribedDate;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId='" + prescriptionId + '\'' +
                ", medicines=" + medicines +
                ", prescribedDate=" + prescribedDate +
                '}';
    }
}
