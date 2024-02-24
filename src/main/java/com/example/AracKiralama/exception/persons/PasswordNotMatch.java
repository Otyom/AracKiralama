package com.example.AracKiralama.exception.persons;

public class PasswordNotMatch extends RuntimeException{

    public PasswordNotMatch(String message) {
        super(message);
    }
    public PasswordNotMatch(){
        super();
    }
}
