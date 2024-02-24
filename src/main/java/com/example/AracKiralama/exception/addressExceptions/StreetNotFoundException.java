package com.example.AracKiralama.exception.addressExceptions;

public class StreetNotFoundException extends RuntimeException{

    public StreetNotFoundException() {
        super();
    }

    public StreetNotFoundException(String message) {
        super(message);
    }
}
