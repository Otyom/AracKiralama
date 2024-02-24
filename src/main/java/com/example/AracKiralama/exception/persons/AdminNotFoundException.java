package com.example.AracKiralama.exception.persons;

public class AdminNotFoundException extends RuntimeException{

    public AdminNotFoundException() {
        super();
    }


    public AdminNotFoundException(String message) {
        super(message);
    }
}
