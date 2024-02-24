package com.example.AracKiralama.exception.rentacarExceptions;

public class InvalidPhoneNumberException extends RuntimeException{

    public InvalidPhoneNumberException(String message){
        super(message);
    }
    public InvalidPhoneNumberException(){
        super();
    }
}
