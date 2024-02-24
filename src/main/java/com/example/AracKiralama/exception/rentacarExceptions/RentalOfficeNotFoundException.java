package com.example.AracKiralama.exception.rentacarExceptions;

public class RentalOfficeNotFoundException extends RuntimeException{
    public RentalOfficeNotFoundException() {
        super();
    }

    public RentalOfficeNotFoundException(String message) {
        super(message);
    }

}
