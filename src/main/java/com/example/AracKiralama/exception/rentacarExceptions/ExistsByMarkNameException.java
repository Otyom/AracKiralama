package com.example.AracKiralama.exception.rentacarExceptions;

public class ExistsByMarkNameException extends RuntimeException{

    public ExistsByMarkNameException(String message) {
        super(message);
    }
    public ExistsByMarkNameException(){
        super();
    }
}
