package com.healthcare.digital;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
            System.err.println("Doctor with ID " + doctor.getId() + " already exists");
            return;
        }
        doctors.put(doctor.getId(), doctor);
        System.out.println("Doctor added successfully: " + doctor.getName());
    }

    public void removeDoctor(String doctorId) throws DoctorNotAvailableException {
        if (!doctors.containsKey(doctorId)) {
            throw new DoctorNotAvailableException("Doctor not found with ID: " + doctorId);
        }
        
        boolean hasAppointments = appointments.stream()
            .anyMatch(apt -> apt.getDoctor().getId().equals(doctorId));
        
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
        // Using Java 8 streams and forEach with method reference
        doctors.values().stream()
            .forEach(doctor -> System.out.println(
                "ID: " + doctor.getId() + 
                " | Name: " + doctor.getName() + 
                " | Specialization: " + doctor.getSpecialization() + 
                " | Experience: " + doctor.getExperience() + " years"
            ));
        System.out.println("---------------------------");
    }

    public void bookAppointment(String appointmentId, String patientId, String doctorId, 
                                LocalDate date, LocalTime time) 
            throws InvalidUserException, DoctorNotAvailableException {
        
        Patient patient = Optional.ofNullable(patients.get(patientId))
            .orElseThrow(() -> new InvalidUserException("Patient not found with ID: " + patientId));

        Doctor doctor = Optional.ofNullable(doctors.get(doctorId))
            .orElseThrow(() -> new DoctorNotAvailableException("Doctor not found with ID: " + doctorId));

        boolean isAvailable = appointments.stream()
            .noneMatch(apt -> apt.getDoctor().getId().equals(doctorId) && 
                             apt.getDate().equals(date) && 
                             apt.getTime().equals(time));
        
        if (!isAvailable) {
            throw new DoctorNotAvailableException(
                "Doctor " + doctor.getName() + " is not available at " + time + " on " + date);
        }

        Appointment appointment = new Appointment(appointmentId, patient, doctor, date, time);
        appointments.add(appointment);
        System.out.println(appointment);
        System.out.println("Appointment booked successfully!");
    }

    public void addPrescription(String patientId, Prescription prescription) 
            throws InvalidUserException {
        
        Patient patient = Optional.ofNullable(patients.get(patientId))
            .orElseThrow(() -> new InvalidUserException("Patient not found with ID: " + patientId));

        patient.addPrescription(prescription);
        System.out.println("Prescription added for patient: " + patient.getName());
    }

    public void addMedicalRecord(String patientId, MedicalRecord record) 
            throws InvalidUserException {
        
        Patient patient = Optional.ofNullable(patients.get(patientId))
            .orElseThrow(() -> new InvalidUserException("Patient not found with ID: " + patientId));

        patient.addMedicalRecord(record);
        
        List<MedicalRecord> records = medicalRecords.get(patientId);
        records.add(record);
        
        System.out.println("Medical record added for patient: " + patient.getName());
    }

    public Patient getPatient(String patientId) throws InvalidUserException {
        return Optional.ofNullable(patients.get(patientId))
            .orElseThrow(() -> new InvalidUserException("Patient not found with ID: " + patientId));
    }

    public Doctor getDoctor(String doctorId) throws DoctorNotAvailableException {
        return Optional.ofNullable(doctors.get(doctorId))
            .orElseThrow(() -> new DoctorNotAvailableException("Doctor not found with ID: " + doctorId));
    }

    public List<Appointment> getAppointmentsByPatient(String patientId) {
        return appointments.stream()
            .filter(apt -> apt.getPatient().getId().equals(patientId))
            .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        return appointments.stream()
            .filter(apt -> apt.getDoctor().getId().equals(doctorId))
            .collect(Collectors.toList());
    }

    public void cancelAppointment(String appointmentId) throws AppointmentNotFoundException {
        Optional<Appointment> appointmentToRemove = appointments.stream()
            .filter(apt -> apt.getAppointmentId().equals(appointmentId))
            .findFirst();

        if (!appointmentToRemove.isPresent()) {
            throw new AppointmentNotFoundException(
                "Appointment not found with ID: " + appointmentId);
        }

        appointments.remove(appointmentToRemove.get());
        System.out.println("Appointment cancelled: " + appointmentId);
    }

    public List<Prescription> getPrescriptionsByPatient(String patientId) 
            throws InvalidUserException {
        
        Patient patient = Optional.ofNullable(patients.get(patientId))
            .orElseThrow(() -> new InvalidUserException("Patient not found with ID: " + patientId));
        
        List<Prescription> prescriptions = patient.getPrescriptions();
        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions found for patient: " + patient.getName());
        } else {
            System.out.println("Prescriptions for " + patient.getName() + ":");
            prescriptions.forEach(System.out::println);
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

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctors.values().stream()
            .filter(doctor -> doctor.getSpecialization().equalsIgnoreCase(specialization))
            .collect(Collectors.toList());
    }

    public List<Appointment> getUpcomingAppointments(LocalDate date) {
        return appointments.stream()
            .filter(apt -> !apt.getDate().isBefore(date))
            .sorted(Comparator.comparing(Appointment::getDate)
                    .thenComparing(Appointment::getTime))
            .collect(Collectors.toList());
    }

    public Map<String, Long> getAppointmentCountByDoctor() {
        return appointments.stream()
            .collect(Collectors.groupingBy(
                apt -> apt.getDoctor().getName(),
                Collectors.counting()
            ));
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