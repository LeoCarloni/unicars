package edu.unifacef.unicars.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String error){
        super(error);
    }
}
