package com.example.AracKiralama.exception.rentacarExceptions;

public class ExistsByModelException extends RuntimeException {
    public ExistsByModelException(String message){
        super(message);
    }
    public ExistsByModelException(){
        super();
    }
}
