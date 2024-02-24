package com.example.AracKiralama.exception.persons;

public class InvaildToken extends RuntimeException{

    public InvaildToken() {
        super();
    }

    public InvaildToken(String message) {
        super(message);
    }
}
