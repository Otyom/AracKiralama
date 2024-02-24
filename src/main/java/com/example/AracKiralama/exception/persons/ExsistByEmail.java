package com.example.AracKiralama.exception.persons;

public class ExsistByEmail extends  RuntimeException{

    public ExsistByEmail(String message) {
        super(message);
    }
    public ExsistByEmail(){
        super();
    }
}
