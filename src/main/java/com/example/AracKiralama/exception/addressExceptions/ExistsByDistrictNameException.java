package com.example.AracKiralama.exception.addressExceptions;




public class ExistsByDistrictNameException extends RuntimeException{
    public ExistsByDistrictNameException(String message){
        super(message);
    }
    public ExistsByDistrictNameException(){
        super();
    }
}
