package com.example.AracKiralama.exception.addressExceptions;

public class ExistsByNeighborhoodNameException extends RuntimeException{
    public ExistsByNeighborhoodNameException(String message){
        super(message);
    }
    public ExistsByNeighborhoodNameException(){
        super();
    }
}
