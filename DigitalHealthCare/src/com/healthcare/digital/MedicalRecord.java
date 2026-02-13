package com.healthcare.digital;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private String recordId;
    private String diagnosis;
    private List<LocalDate> recordDate;

    public MedicalRecord(String recordId, String diagnosis) {
        this.recordId = recordId;
        this.diagnosis = diagnosis;
        this.recordDate = new ArrayList<>();
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<LocalDate> getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(List<LocalDate> recordDate) {
        this.recordDate = recordDate;
    }

    public void addRecordDate(LocalDate date) {
        this.recordDate.add(date);
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", recordDate=" + recordDate +
                '}';
    }
}