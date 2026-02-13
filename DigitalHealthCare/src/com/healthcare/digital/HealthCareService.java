package com.healthcare.digital;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class HealthCareService {
    private Map<String, Patient> patients;
    private Map<String, Doctor> doctors;
    private List<Appointment> appointments;
    private Map<String, List<MedicalRecord>> medicalRecords;

    public HealthCareService() {
        this.patients = new HashMap<>();
        this.doctors = new HashMap<>();
        this.appointments = new ArrayList<>();
        this.medicalRecords = new HashMap<>();
    }

    public void registerPatient(Patient patient) throws InvalidUserException {
        if (patient == null || patient.getId() == null || patient.getId().isEmpty()) {
            throw new InvalidUserException("Invalid patient information");
        }
        if (patients.containsKey(patient.getId())) {
            throw new InvalidUserException("Patient with ID " + patient.getId() + " already exists");
        }
        patients.put(patient.getId(), patient);
        medicalRecords.put(patient.getId(), new ArrayList<>());
        System.out.println("Patient registered successfully: " + patient.getName());
    }

    public void addDoctor(Doctor doctor) throws InvalidUserException {
        if (doctor == null || doctor.getId() == null || doctor.getId().isEmpty()) {
            throw new InvalidUserException("Invalid doctor information");
        }
        if (doctors.containsKey(doctor.getId())) {
            //throw new InvalidUserException("Doctor with ID " + doctor.getId() + " already exists");
        	System.err.println("Doctor with ID " + doctor.getId() + " already exists");
        }
        doctors.put(doctor.getId(), doctor);
        System.out.println("Doctor added successfully: " + doctor.getName());
    }

    public void removeDoctor(String doctorId) throws DoctorNotAvailableException {
        if (!doctors.containsKey(doctorId)) {
            throw new DoctorNotAvailableException("Doctor not found with ID: " + doctorId);
        }
        
        // Check if doctor has any appointments
        boolean hasAppointments = false;
        for (Appointment apt : appointments) {
            if (apt.getDoctor().getId().equals(doctorId)) {
                hasAppointments = true;
                break;
            }
        }
        
        if (hasAppointments) {
            System.out.println("Warning: Doctor has existing appointments. Removing anyway...");
        }
        
        Doctor removed = doctors.remove(doctorId);
        System.out.println("Doctor removed successfully: " + removed.getName());
    }

    public void viewAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }
        
        System.out.println("\n--- List of All Doctors ---");
        for (Doctor doctor : doctors.values()) {
            System.out.println("ID: " + doctor.getId() + 
                             " | Name: " + doctor.getName() + 
                             " | Specialization: " + doctor.getSpecialization() + 
                             " | Experience: " + doctor.getExperience() + " years");
        }
        System.out.println("---------------------------");
    }

    public void bookAppointment(String appointmentId, String patientId, String doctorId, 
                                LocalDate date, LocalTime time) 
            throws InvalidUserException, DoctorNotAvailableException {
        
        Patient patient = patients.get(patientId);
        if (patient == null) {
            throw new InvalidUserException("Patient not found with ID: " + patientId);
        }

        Doctor doctor = doctors.get(doctorId);
        if (doctor == null) {
            throw new DoctorNotAvailableException("Doctor not found with ID: " + doctorId);
        }

        
        for (Appointment apt : appointments) {
            if (apt.getDoctor().getId().equals(doctorId) && 
                apt.getDate().equals(date) && apt.getTime().equals(time)) {
                throw new DoctorNotAvailableException(
                    "Doctor " + doctor.getName() + " is not available at " + time + " on " + date);
            }
        }

        Appointment appointment = new Appointment(appointmentId, patient, doctor, date, time);
        appointments.add(appointment);
        System.out.println(appointment);
        System.out.println("Appointment booked successfully!");
    }

    public void addPrescription(String patientId, Prescription prescription) 
            throws InvalidUserException {
        
        Patient patient = patients.get(patientId);
        if (patient == null) {
            throw new InvalidUserException("Patient not found with ID: " + patientId);
        }

        patient.addPrescription(prescription);
        System.out.println("Prescription added for patient: " + patient.getName());
    }

    public void addMedicalRecord(String patientId, MedicalRecord record) 
            throws InvalidUserException {
        
        Patient patient = patients.get(patientId);
        if (patient == null) {
            throw new InvalidUserException("Patient not found with ID: " + patientId);
        }

        patient.addMedicalRecord(record);
        
        List<MedicalRecord> records = medicalRecords.get(patientId);
        records.add(record);
        
        System.out.println("Medical record added for patient: " + patient.getName());
    }

    public Patient getPatient(String patientId) throws InvalidUserException {
        Patient patient = patients.get(patientId);
        if (patient == null) {
            throw new InvalidUserException("Patient not found with ID: " + patientId);
        }
        return patient;
    }

    public Doctor getDoctor(String doctorId) throws DoctorNotAvailableException {
        Doctor doctor = doctors.get(doctorId);
        if (doctor == null) {
            throw new DoctorNotAvailableException("Doctor not found with ID: " + doctorId);
        }
        return doctor;
    }

    public List<Appointment> getAppointmentsByPatient(String patientId) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment apt : appointments) {
            if (apt.getPatient().getId().equals(patientId)) {
                patientAppointments.add(apt);
            }
           
        }
        return patientAppointments;
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment apt : appointments) {
            if (apt.getDoctor().getId().equals(doctorId)) {
                doctorAppointments.add(apt);
            }
        }
        return doctorAppointments;
    }

    public void cancelAppointment(String appointmentId) throws AppointmentNotFoundException {
        Appointment appointmentToRemove = null;
        for (Appointment apt : appointments) {
            if (apt.getAppointmentId().equals(appointmentId)) {
                appointmentToRemove = apt;
                break;
            }
        }

        if (appointmentToRemove == null) {
            throw new AppointmentNotFoundException(
                "Appointment not found with ID: " + appointmentId);
        }

        appointments.remove(appointmentToRemove);
        System.out.println("Appointment cancelled: " + appointmentId);
    }

    public List<Prescription> getPrescriptionsByPatient(String patientId) 
            throws InvalidUserException {
        
        Patient patient = patients.get(patientId);
        if (patient == null) {
            throw new InvalidUserException("Patient not found with ID: " + patientId);
        }
        
        List<Prescription> prescriptions = patient.getPrescriptions();
        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions found for patient: " + patient.getName());
        } else {
            System.out.println("Prescriptions for " + patient.getName() + ":");
            for (Prescription p : prescriptions) {
                System.out.println(p);
            }
        }
        return prescriptions;
    }

    public List<MedicalRecord> getMedicalRecordsByPatient(String patientId) 
            throws MedicalRecordNotFoundException {
        
        List<MedicalRecord> records = medicalRecords.get(patientId);
        if (records == null || records.isEmpty()) {
            throw new MedicalRecordNotFoundException(
                "No medical records found for patient ID: " + patientId);
        }
        return records;
    }

    public Map<String, Patient> getPatients() {
        return patients;
    }

    public Map<String, Doctor> getDoctors() {
        return doctors;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}