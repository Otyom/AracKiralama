package com.example.AracKiralama.exception.rentacarExceptions;

public class CarClassNotFoundException extends RuntimeException{

    public CarClassNotFoundException(String message) {
        super(message);
    }

    public CarClassNotFoundException(){
        super();
    }
}
