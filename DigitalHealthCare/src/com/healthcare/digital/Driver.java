package com.healthcare.digital;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

public class Driver {

    
    private static String getValidInput(Scanner scanner, String prompt, Predicate<String> validator, String errorMessage) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.next();
                if (validator.test(input)) {
                    return input;
                }
                System.out.println(errorMessage);
            } catch (Exception e) {
                System.out.println("Error reading input. Please try again.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

  
    private static int getValidIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                if (value < 0) {
                    System.out.println("Value cannot be negative. Please try again.");
                    continue;
                }
                return value;
            } catch (Exception e) {
                System.out.println("Invalid number format. Please enter a valid number.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

   
    private static LocalDate getValidDateInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String dateStr = scanner.next();
                return LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD format (e.g., 2024-12-25)");
            } catch (Exception e) {
                System.out.println("Error reading date. Please try again.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

    
    private static LocalTime getValidTimeInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String timeStr = scanner.next();
                return LocalTime.parse(timeStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:MM format (e.g., 14:30)");
            } catch (Exception e) {
                System.out.println("Error reading time. Please try again.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

    private static String getValidLineInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (!input.isEmpty()) {
                    return input;
                }
                System.out.println("Input cannot be empty. Please try again.");
            } catch (Exception e) {
                System.out.println("Error reading input. Please try again.");
            }
        }
    }

    private static void displayAppointments(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            System.out.println("\n--- Appointments ---");
            appointments.stream()
                .forEach(System.out::println);
            System.out.println("--------------------\n");
        }
    }

    public static void main(String[] args)
            throws DoctorNotAvailableException, InvalidUserException {

        try {

            Scanner scanner = new Scanner(System.in);
            HealthCareService healthCare = new HealthCareService();
            Admin admin = new Admin("A001", "Admin User",
                    "admin@hospital.com", "admin123");

          
        while(true)
        {
        	  System.out.println("\n=== Digital Health Care System ===");
              System.out.println("Login as Admin and patients");
              System.out.println("Press \n1. Admin");
              System.out.println("2. Patient");
              System.out.println("3. Doctor login");
              System.out.println("4. Exit");

              String input = scanner.next();

            if (input.equalsIgnoreCase("1")) {

                String email = getValidInput(scanner, "Enter email: ", 
                    e -> e.contains("@"), 
                    "Invalid email format. Email must contain '@'");

                String password = getValidInput(scanner, "Enter password: ", 
                    p -> !p.isEmpty(), 
                    "Password cannot be empty");

                if (email.equals(admin.getEmail())
                        && password.equals(admin.password)) {

                    System.out.println("\n=====Admin login successful====");

                    while (true) {

                        System.out.println(
                                "\nPress \n1. Add Doctor \n2. Remove Doctor \n3. View all Doctors \n4. Logout");

                        String adminInput = scanner.next();

                        if (adminInput.equalsIgnoreCase("1")) {

                            try {
                                System.out.println("\nAdd Doctor details:");
                                
                                String doctorId = getValidInput(scanner, "Enter Doctor ID: ", 
                                    id -> !id.isEmpty() && id.length() >= 3, 
                                    "Doctor ID must be at least 3 characters");

                                scanner.nextLine(); // Clear buffer
                                String doctorName = getValidLineInput(scanner, "Enter Doctor Name: ");

                                String doctorEmail = getValidInput(scanner, "Enter Doctor Email: ", 
                                    e -> e.contains("@"), 
                                    "Invalid email format. Email must contain '@'");

                                String specialization = getValidInput(scanner, "Enter Doctor Specialization: ", 
                                    s -> !s.isEmpty(), 
                                    "Specialization cannot be empty");

                                int experience = getValidIntInput(scanner, "Enter Doctor Experience (years): ");

                                Doctor doctor = new Doctor(
                                        doctorId,
                                        doctorName,
                                        doctorEmail,
                                        specialization,
                                        experience);

                                healthCare.addDoctor(doctor);
                            } catch (InvalidUserException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            continue;
                            
                        }

                        else if (adminInput.equalsIgnoreCase("2")) {

                            try {
                                System.out.println("\nRemove Doctor");
                                String doctorId = getValidInput(scanner, "Enter Doctor ID to remove: ", 
                                    id -> !id.isEmpty(), 
                                    "Doctor ID cannot be empty");
                                healthCare.removeDoctor(doctorId);
                            } catch (DoctorNotAvailableException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        else if (adminInput.equalsIgnoreCase("3")) {

                            System.out.println("\nView all Doctors");
                            healthCare.viewAllDoctors();
                        }

                        else if (adminInput.equalsIgnoreCase("4")) {
                            System.out.println("Logging out...");
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
                        "\nPatient registration and login: Press \n1. Register \n2. Login");

                String patientInput = scanner.next();

                if (patientInput.equalsIgnoreCase("1")) {

                    String patientId = "";
                    try {
                        System.out.println("\nPatient registration:");
                        
                        patientId = getValidInput(scanner, "Enter Patient ID: ", 
                            id -> !id.isEmpty() && id.length() >= 3, 
                            "Patient ID must be at least 3 characters");

                        scanner.nextLine(); // Clear buffer
                        String patientName = getValidLineInput(scanner, "Enter Patient Name: ");

                        String patientEmail = getValidInput(scanner, "Enter Patient Email: ", 
                            e -> e.contains("@"), 
                            "Invalid email format. Email must contain '@'");

                        Patient patient = new Patient(
                                patientId,
                                patientName,
                                patientEmail);

                        healthCare.registerPatient(patient);
                        System.out.println(patient);
                    } catch (InvalidUserException e) {
                        System.out.println("Registration failed: " + e.getMessage());
                        continue;
                    }
                    
                    while (true) {

                        System.out.println("\nPatient Dashboard:");
                        System.out.println(
                                "Press \n1. View Appointments"
                                        + "\n2. Book Appointment"
                                        + "\n3. View Prescriptions"
                                        + "\n4. Cancel Appointment"
                                        + "\n5. Logout");

                        String dashboardInput = scanner.next();

                        if (dashboardInput.equalsIgnoreCase("1")) {

                            try {
                                System.out.println("\n--- View Appointments ---");
                                List<Appointment> appointments = healthCare.getAppointmentsByPatient(patientId);
                                displayAppointments(appointments);
                            } catch (Exception e) {
                                System.out.println("Error retrieving appointments: " + e.getMessage());
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("2")) {

                            try {
                                System.out.println("\n--- Book Appointment ---");
                                
                                String apId = getValidInput(scanner, "Enter Appointment ID: ", 
                                    id -> !id.isEmpty(), 
                                    "Appointment ID cannot be empty");
                                
                                String doctorId = getValidInput(scanner, "Enter Doctor ID: ", 
                                    id -> !id.isEmpty(), 
                                    "Doctor ID cannot be empty");

                                LocalDate date = getValidDateInput(scanner, "Enter Appointment Date (YYYY-MM-DD): ");
                                LocalTime time = getValidTimeInput(scanner, "Enter Appointment Time (HH:MM): ");

                                healthCare.bookAppointment(
                                        apId,
                                        patientId,
                                        doctorId,
                                        date,
                                        time);
                            } catch (InvalidUserException | DoctorNotAvailableException e) {
                                System.out.println("Booking failed: " + e.getMessage());
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("3")) {

                            try {
                                System.out.println("\n--- View Prescriptions ---");
                                healthCare.getPrescriptionsByPatient(patientId);
                            } catch (InvalidUserException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("4")) {

                            try {
                                System.out.println("\n--- Cancel Appointment ---");
                                List<Appointment> appointments = healthCare.getAppointmentsByPatient(patientId);
                                
                                if (appointments.isEmpty()) {
                                    System.out.println("No appointments found to cancel.");
                                } else {
                                    displayAppointments(appointments);

                                    String appointmentId = getValidInput(scanner, "Enter Appointment ID to cancel: ", 
                                        id -> !id.isEmpty(), 
                                        "Appointment ID cannot be empty");

                                    healthCare.cancelAppointment(appointmentId);
                                }
                            } catch (AppointmentNotFoundException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("5")) {
                            System.out.println("Logging out...");
                            break;
                        }
                    }
                }

                else if (patientInput.equalsIgnoreCase("2")) {

                    System.out.println("\nPatient login:");
                    String patientId = getValidInput(scanner, "Enter Patient ID: ", 
                        id -> !id.isEmpty(), 
                        "Patient ID cannot be empty");

                    String patientEmail = getValidInput(scanner, "Enter Patient Email: ", 
                        e -> e.contains("@"), 
                        "Invalid email format");

                    // Using Optional for null-safe patient retrieval
                    Optional<Patient> optionalPatient = Optional.ofNullable(healthCare.getPatients().get(patientId));
                    
                    if (!optionalPatient.isPresent() || 
                        !optionalPatient.get().getEmail().equals(patientEmail)) {
                        System.out.println("Invalid patient ID or email. Please try again.");
                        continue;
                    }

                    Patient patient = optionalPatient.get();

                    System.out.println(
                            "Patient login successful. Welcome, "
                                    + patient.getName() + "!");

                    while (true) {

                        System.out.println("\nPatient Dashboard:");
                        System.out.println(
                                "Press \n1. View Appointments"
                                        + "\n2. Book Appointment"
                                        + "\n3. View Prescriptions"
                                        + "\n4. Cancel Appointment"
                                        + "\n5. Logout");

                        String dashboardInput = scanner.next();

                        if (dashboardInput.equalsIgnoreCase("1")) {

                            try {
                                System.out.println("\n--- View Appointments ---");
                                List<Appointment> appointments = healthCare.getAppointmentsByPatient(patientId);
                                displayAppointments(appointments);
                            } catch (Exception e) {
                                System.out.println("Error retrieving appointments: " + e.getMessage());
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("2")) {

                            try {
                                System.out.println("\n--- Book Appointment ---");
                                
                                String apId = getValidInput(scanner, "Enter Appointment ID: ", 
                                    id -> !id.isEmpty(), 
                                    "Appointment ID cannot be empty");
                                
                                String doctorId = getValidInput(scanner, "Enter Doctor ID: ", 
                                    id -> !id.isEmpty(), 
                                    "Doctor ID cannot be empty");

                                LocalDate date = getValidDateInput(scanner, "Enter Appointment Date (YYYY-MM-DD): ");
                                LocalTime time = getValidTimeInput(scanner, "Enter Appointment Time (HH:MM): ");

                                healthCare.bookAppointment(
                                        apId,
                                        patientId,
                                        doctorId,
                                        date,
                                        time);
                            } catch (InvalidUserException | DoctorNotAvailableException e) {
                                System.out.println("Booking failed: " + e.getMessage());
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("3")) {

                            try {
                                System.out.println("\n--- View Prescriptions ---");
                                healthCare.getPrescriptionsByPatient(patientId);
                            } catch (InvalidUserException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("4")) {

                            try {
                                System.out.println("\n--- Cancel Appointment ---");
                                List<Appointment> appointments = healthCare.getAppointmentsByPatient(patientId);
                                
                                if (appointments.isEmpty()) {
                                    System.out.println("No appointments found to cancel.");
                                } else {
                                    displayAppointments(appointments);

                                    String appointmentId = getValidInput(scanner, "Enter Appointment ID to cancel: ", 
                                        id -> !id.isEmpty(), 
                                        "Appointment ID cannot be empty");

                                    healthCare.cancelAppointment(appointmentId);
                                }
                            } catch (AppointmentNotFoundException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        else if (dashboardInput.equalsIgnoreCase("5")) {
                            System.out.println("Logging out...");
                            break;
                        }
                    }
                }
            }
            else if(input.equals("3"))
            {
                String doctorId = getValidInput(scanner, "Enter Doctor ID: ", 
                    id -> !id.isEmpty(), 
                    "Doctor ID cannot be empty");

                 try {
                     Doctor doctor = healthCare.getDoctor(doctorId);
                     System.out.println("\n=====Doctor login successful====");
                     System.out.println("Welcome, Dr. " + doctor.getName() + "!");
                 } catch (DoctorNotAvailableException e) {
                     System.out.println("Error: " + e.getMessage());
                     continue;
                 }

            	 while (true) {

                     System.out.println(
                             "\nDoctor Dashboard:");
                     System.out.println(
                             "Press \n1. View Appointments"
                                     + "\n2. View Prescriptions"
                                     + "\n3. Logout");

                     String doctorInput = scanner.next();

                     if (doctorInput.equalsIgnoreCase("1")) {

                         try {
                             System.out.println("\n--- View Doctor's Appointments ---");
                             
                             List<Appointment> appointments = healthCare.getAppointmentsByDoctor(doctorId);
                             
                             if(appointments.isEmpty()) {
                                 System.out.println("No appointments found for doctor with ID: " + doctorId);
                             }
                             else {
                                 System.out.println("Appointments for Doctor ID: " + doctorId);
                                 
                                 // Display appointments grouped by patient using streams
                                 appointments.stream()
                                     .forEach(System.out::println);
                                 
                                 String addPrescription = getValidInput(scanner, 
                                     "\nDo you want to add a prescription? (yes/no): ",
                                     input2 -> input2.equalsIgnoreCase("yes") || input2.equalsIgnoreCase("no"),
                                     "Please enter 'yes' or 'no'");
                                 
                                 if (addPrescription.equalsIgnoreCase("yes")) {
                                     String patientId = getValidInput(scanner, "Enter the patient ID: ",
                                         id -> !id.isEmpty(),
                                         "Patient ID cannot be empty");
                                     
                                     System.out.println("\n--- Adding prescription ---");
                                     String prescriptionId = getValidInput(scanner, "Enter ID for prescription: ",
                                         id -> !id.isEmpty(),
                                         "Prescription ID cannot be empty");
                                     
                                     LocalDate date = getValidDateInput(scanner, "Enter the date (YYYY-MM-DD): ");
                                     
                                     scanner.nextLine(); // Clear buffer
                                     String medicine = getValidLineInput(scanner, "Enter medicine name: ");
                                     
                                     Prescription prescription = new Prescription(prescriptionId, date);
                                     prescription.addMedicine(medicine);
                                     healthCare.addPrescription(patientId, prescription);
                                 }
                             }
                         } catch (InvalidUserException e) {
                             System.out.println("Error: " + e.getMessage());
                         }
                     }

                     else if (doctorInput.equalsIgnoreCase("2")) {

                         try {
                             System.out.println("\n--- View Prescriptions ---");
                             String patientId = getValidInput(scanner, "Enter the patient ID: ",
                                 id -> !id.isEmpty(),
                                 "Patient ID cannot be empty");
                             healthCare.getPrescriptionsByPatient(patientId);
                         } catch (InvalidUserException e) {
                             System.out.println("Error: " + e.getMessage());
                         }
                     }

                     else if (doctorInput.equalsIgnoreCase("3")) {
                         System.out.println("Logging out...");
                         break;
                     }
                 }
            }
            else if(input.equals("4"))
            {
            	System.out.println("\n***********Thanks for using Digital Healthcare********* ");
            	break;
            }
            else {
                System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
            }

        }
         }

        catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}