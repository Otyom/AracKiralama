package com.example.AracKiralama.exception.rentacarExceptions;

public class CarRentedException extends RuntimeException{
    public CarRentedException(String message) {
        super(message);
    }
    public CarRentedException(){
        super();
    }
}
