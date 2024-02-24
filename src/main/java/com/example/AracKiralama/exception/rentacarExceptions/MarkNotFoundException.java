package com.example.AracKiralama.exception.rentacarExceptions;

public class MarkNotFoundException extends RuntimeException{

    public MarkNotFoundException(String message) {
        super(message);
    }
    public MarkNotFoundException(){
        super();
    }
}
