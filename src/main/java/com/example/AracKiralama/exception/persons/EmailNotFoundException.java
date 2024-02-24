package com.example.AracKiralama.exception.persons;

public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException(String message) {
        super(message);
    }
    public EmailNotFoundException() {
        super();
    }

}
