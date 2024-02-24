package com.example.AracKiralama.exception.rentacarExceptions;

public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(String message) {
        super(message);
    }
    public ModelNotFoundException(){
        super();
    }
}
