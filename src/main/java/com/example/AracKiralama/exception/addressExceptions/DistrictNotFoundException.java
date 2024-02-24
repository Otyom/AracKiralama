package com.example.AracKiralama.exception.addressExceptions;

public class DistrictNotFoundException extends RuntimeException{
    public DistrictNotFoundException(String message){
        super(message);
    }

    public DistrictNotFoundException(){
        super();
    }
}
