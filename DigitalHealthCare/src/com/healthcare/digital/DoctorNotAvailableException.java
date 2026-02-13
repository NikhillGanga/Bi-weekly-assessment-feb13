package com.healthcare.digital;

public class DoctorNotAvailableException extends Exception {
    public DoctorNotAvailableException(String message) {
        super(message);
    }
}