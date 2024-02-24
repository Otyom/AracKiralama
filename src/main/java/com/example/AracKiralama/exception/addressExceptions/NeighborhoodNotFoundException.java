package com.example.AracKiralama.exception.addressExceptions;

public class NeighborhoodNotFoundException extends RuntimeException{
    public NeighborhoodNotFoundException(String message){
        super(message);
    }
    public NeighborhoodNotFoundException(){
        super();
    }
}
