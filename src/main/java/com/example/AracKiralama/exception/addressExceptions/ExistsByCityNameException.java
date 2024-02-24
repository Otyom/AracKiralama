package com.example.AracKiralama.exception.addressExceptions;

public class ExistsByCityNameException extends RuntimeException{
    public ExistsByCityNameException(String message){
        super(message);
    }
    public ExistsByCityNameException(){
        super();
    }
}
