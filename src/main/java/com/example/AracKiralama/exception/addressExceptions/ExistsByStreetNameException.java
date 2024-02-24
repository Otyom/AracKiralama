package com.example.AracKiralama.exception.addressExceptions;

public class ExistsByStreetNameException extends RuntimeException {

    public ExistsByStreetNameException(String message) {
        super(message);
    }
    public ExistsByStreetNameException(){
        super();
    }
}
