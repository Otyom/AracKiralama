package com.example.AracKiralama.exception.addressExceptions;

public class StreetIsEmptyException extends RuntimeException{

    public StreetIsEmptyException(String message) {
        super(message);
    }
    public StreetIsEmptyException() {
        super();
    }
}
