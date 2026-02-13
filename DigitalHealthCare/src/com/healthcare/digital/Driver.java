package com.healthcare.digital;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args)
            throws DoctorNotAvailableException, InvalidUserException {

        try {

            Scanner scanner = new Scanner(System.in);
            HealthCareService healthCare = new HealthCareService();
            Admin admin = new Admin("A001", "Admin User",
                    "admin@hospital.com", "admin123");

          
        while(true)
        {
        	  System.out.println("=== Digital Health Care System ===");
              System.out.println("Login as Admin and patients");
              System.out.println("Press \n1. Admin");
              System.out.println("2. Patient");
              System.out.println("3.Doctor login ");
              System.out.println("4. Exit");

              String input = scanner.next();

            if (input.equalsIgnoreCase("1")) {

                System.out.print("Enter email:");
                String email = scanner.next();

                System.out.print("Enter password:");
                String password = scanner.next();

                if (email.equals(admin.getEmail())
                        && password.equals(admin.password)) {

                    System.out.println("=====Admin login successful====");

                    while (true) {

                        System.out.println(
                                "Press \n1. Add Doctor \n2. Remove Doctor \n3. View all Doctors \n4. Logout");

                        String adminInput = scanner.next();

                        if (adminInput.equalsIgnoreCase("1")) {

                            System.out.println("Add Doctor details:");
                            System.out.print("Enter Doctor ID:");
                            String doctorId = scanner.next();

                            System.out.print("Enter Doctor Name:");
                            scanner.nextLine();
                            String doctorName = scanner.nextLine();

                            System.out.print("Enter Doctor Email:");
                            String doctorEmail = scanner.next();

                            System.out.print("Enter Doctor Specialization:");
                            String specialization = scanner.next();

                            System.out.print("Enter Doctor Experience:");
                            int experience = scanner.nextInt();

                            Doctor doctor = new Doctor(
                                    doctorId,
                                    doctorName,
                                    doctorEmail,
                                    specialization,
                                    experience);

                            healthCare.addDoctor(doctor);
                            continue;
                            
                        }

                        else if (adminInput.equalsIgnoreCase("2")) {

                            System.out.println("Remove Doctor");
                            System.out.print("Enter Doctor ID to remove:");
                            String doctorId = scanner.next();
                            healthCare.removeDoctor(doctorId);
                        }

                        else if (adminInput.equalsIgnoreCase("3")) {

                            System.out.println("View all Doctors");
                            healthCare.viewAllDoctors();
                        }

                        else if (adminInput.equalsIgnoreCase("4")) {
                            break;
                        }
                    }
                }

                else {
                    System.out.println("Invalid email or password. Please try again.");
                    continue;
                }

                
            }

            else if (input.equalsIgnoreCase("2")) {

                System.out.println(
                        "Patient registration and login: Press \n1. Register \n2. Login");

                String patientInput = scanner.next();

                if (patientInput.equalsIgnoreCase("1")) {

                    System.out.println("Patient registration:");
                    System.out.print("Enter Patient ID:");
                    String patientId = scanner.next();

                    System.out.print("Enter Patient Name:");
                    scanner.nextLine();
                    String patientName = scanner.nextLine();

                    System.out.print("Enter Patient Email:");
                    String patientEmail = scanner.next();

                    Patient patient = new Patient(
                            patientId,
                            patientName,
                            patientEmail);

                    healthCare.registerPatient(patient);
                    System.out.println(patient);
                    while (true) {

                        System.out.println("Patient Dashboard:");
                        System.out.println(
                                "Press \n1. View Appointments"
                                        + "\n2. Book Appointment"
                                        + "\n3. View Prescriptions"
                                        + "\n4. Cancel Appointment"
                                        + "\n5. Logout");

                        String dashboardInput = scanner.next();

                        if (dashboardInput.equalsIgnoreCase("1")) {

                            System.out.println("View Appointments");
                            List<Appointment> appointments = healthCare.getAppointmentsByPatient(patientId);
                            if (appointments.isEmpty()) {
                                System.out.println("No appointments found.");
                            } else {
                                for (Appointment apt : appointments) {
                                    System.out.println(apt);
                                }
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("2")) {

                            System.out.println("Book Appointment");
                            System.out.println("Enter Appointment Id:");
                            String apId = scanner.next();
                            System.out.print("Enter Doctor ID:");
                            String doctorId = scanner.next();

                            System.out.print(
                                    "Enter Appointment Date (YYYY-MM-DD):");
                            String dateStr = scanner.next();
                            LocalDate date =
                                    LocalDate.parse(dateStr);

                            System.out.print(
                                    "Enter Appointment Time (HH:MM):");
                            String timeStr = scanner.next();
                            LocalTime time =
                                    LocalTime.parse(timeStr);

                            healthCare.bookAppointment(
                                    apId,
                                    patientId,
                                    doctorId,
                                    date,
                                    time);
                        }

                        else if (dashboardInput.equalsIgnoreCase("3")) {

                            System.out.println("View Prescriptions");
                            healthCare.getPrescriptionsByPatient(patientId);
                        }

                        else if (dashboardInput.equalsIgnoreCase("4")) {

                            System.out.println("View Appointments");
                            List<Appointment> appointments = healthCare.getAppointmentsByPatient(patientId);
                            if (appointments.isEmpty()) {
                                System.out.println("No appointments found to cancel.");
                            } else {
                                for (Appointment apt : appointments) {
                                    System.out.println(apt);
                                }

                                System.out.println("Cancel Appointment");
                                System.out.print(
                                        "Enter Appointment ID to cancel:");

                                String appointmentId =
                                        scanner.next();

                                healthCare.cancelAppointment(
                                        appointmentId);
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("5")) {
                            break;
                        }
                    }
                }

                else if (patientInput.equalsIgnoreCase("2")) {

                    System.out.println("Patient login:");
                    System.out.print("Enter Patient ID:");
                    String patientId = scanner.next();

                    System.out.print("Enter Patient Email:");
                    String patientEmail = scanner.next();

                    if (!healthCare.getPatients().containsKey(patientId)
                            || !healthCare.getPatients().get(patientId)
                            .getEmail().equals(patientEmail)) {

                        System.out.println(
                                "Invalid patient ID or email. Please try again.");
                        continue;
                    }

                    Patient patient =
                            healthCare.getPatients().get(patientId);

                    System.out.println(
                            "Patient login successful. Welcome, "
                                    + patient.getName() + "!");

                    while (true) {

                        System.out.println("Patient Dashboard:");
                        System.out.println(
                                "Press \n1. View Appointments"
                                        + "\n2. Book Appointment"
                                        + "\n3. View Prescriptions"
                                        + "\n4. Cancel Appointment"
                                        + "\n5. Logout");

                        String dashboardInput = scanner.next();

                        if (dashboardInput.equalsIgnoreCase("1")) {

                            System.out.println("View Appointments");
                            List<Appointment> appointments = healthCare.getAppointmentsByPatient(patientId);
                            if (appointments.isEmpty()) {
                                System.out.println("No appointments found.");
                            } else {
                                for (Appointment apt : appointments) {
                                    System.out.println(apt);
                                }
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("2")) {

                            System.out.println("Book Appointment");
                            System.out.println("Enter Appointment Id:");
                            String apId = scanner.next();
                            System.out.print("Enter Doctor ID:");
                            String doctorId = scanner.next();

                            System.out.print(
                                    "Enter Appointment Date (YYYY-MM-DD):");
                            String dateStr = scanner.next();
                            LocalDate date =
                                    LocalDate.parse(dateStr);

                            System.out.print(
                                    "Enter Appointment Time (HH:MM):");
                            String timeStr = scanner.next();
                            LocalTime time =
                                    LocalTime.parse(timeStr);

                            healthCare.bookAppointment(
                                    apId,
                                    patientId,
                                    doctorId,
                                    date,
                                    time);
                        }

                        else if (dashboardInput.equalsIgnoreCase("3")) {

                            System.out.println("View Prescriptions");
                            healthCare.getPrescriptionsByPatient(patientId);
                        }

                        else if (dashboardInput.equalsIgnoreCase("4")) {

                            System.out.println("View Appointments");
                            List<Appointment> appointments = healthCare.getAppointmentsByPatient(patientId);
                            if (appointments.isEmpty()) {
                                System.out.println("No appointments found to cancel.");
                            } else {
                                for (Appointment apt : appointments) {
                                    System.out.println(apt);
                                }

                                System.out.println("Cancel Appointment");
                                System.out.print(
                                        "Enter Appointment ID to cancel:");

                                String appointmentId =
                                        scanner.next();

                                healthCare.cancelAppointment(
                                        appointmentId);
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("5")) {
                            break;
                        }
                    }
                }
            }
            else if(input.equals("3"))
            {
            	  System.out.print("Enter Doctor ID:");
                  String doctorId = scanner.next();

                 // Verify doctor exists
                 try {
                     healthCare.getDoctor(doctorId);
                 } catch (DoctorNotAvailableException e) {
                     System.out.println("Doctor ID is invalid");
                     continue;
                 }

                 System.out.println("=====Doctor login successful====");
            	 System.out.println("Doctor Dashboard:");
                 while (true) {

                     System.out.println(
                             "Press \n1. View Appointments"
                                     + "\n2. View Prescriptions"
                                     + "\n3. Logout");

                     String doctorInput = scanner.next();

                     if (doctorInput.equalsIgnoreCase("1")) {

                         System.out.println("View Appointments");
                         List<Appointment> appointments = healthCare.getAppointmentsByDoctor(doctorId);
                         
                         if(appointments.isEmpty()) {
                             System.out.println("No appointments found for doctor with ID: " + doctorId);
                         }
                         else {
                             System.out.println("Appointments for doctor with ID: " + doctorId);
                             for (Appointment apt : appointments) {
                                 System.out.println(apt);
                             }
                             
                             System.out.print("Do you want to add a prescription? (yes/no): ");
                             String addPrescription = scanner.next();
                             
                             if (addPrescription.equalsIgnoreCase("yes")) {
                                 System.out.print("Enter the patient ID:");
                                 String patientId = scanner.next();
                                 System.out.println("Adding prescription");
                                 System.out.print("Enter ID for prescription: ");
                                 String id = scanner.next();
                                 System.out.print("Enter the date (YYYY-MM-DD): ");
                                 String date1 = scanner.next();
                                 LocalDate date = LocalDate.parse(date1);
                                 
                                 Prescription prescription = new Prescription(id, date);
                                 System.out.print("Enter medicine name: ");
                                 scanner.nextLine(); // consume newline
                                 String medicine = scanner.nextLine();
                                 prescription.addMedicine(medicine);
                                 healthCare.addPrescription(patientId, prescription);
                             }
                         }
                     }

                     else if (doctorInput.equalsIgnoreCase("2")) {

                         System.out.println("View Prescriptions");
                         System.out.print("Enter the patient ID: ");
                         String patientId = scanner.next();
                         healthCare.getPrescriptionsByPatient(patientId);
                     }

                     else if (doctorInput.equalsIgnoreCase("3")) {
                         break;
                     }
                 }
            }
            else if(input.equals("4"))
            {
            	System.out.println("***********Thanks for using Digital Healthcare********* ");
            	break;
            }

        }
         }

        catch (InvalidUserException e) {
            System.out.println(e.getMessage());
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}