package com.example.AracKiralama.exception.addressExceptions;

public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException(String message) {
        super(message);
    }
    public CityNotFoundException(){
        super();
    }
}
