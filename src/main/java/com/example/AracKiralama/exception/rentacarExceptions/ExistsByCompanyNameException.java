package com.example.AracKiralama.exception.rentacarExceptions;

public class ExistsByCompanyNameException extends RuntimeException{
    public ExistsByCompanyNameException(String message){
        super(message);
    }
    public ExistsByCompanyNameException(){
        super();
    }
}
