package com.example.AracKiralama.exception.rentacarExceptions;

public class ExistsByClasNameException extends RuntimeException{

    public ExistsByClasNameException(String message) {
        super(message);
    }
    public ExistsByClasNameException(){
        super();
    }
}
