package com.example.AracKiralama.exception.rentacarExceptions;

public class RentalCompanyNotFoundException extends RuntimeException {
    public RentalCompanyNotFoundException(String message){
        super(message);

    }
    public RentalCompanyNotFoundException(){
        super();
    }
}
