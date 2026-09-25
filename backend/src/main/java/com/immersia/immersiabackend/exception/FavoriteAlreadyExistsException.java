package com.immersia.immersiabackend.exception;

public class FavoriteAlreadyExistsException extends RuntimeException{
    public FavoriteAlreadyExistsException(String message){
        super(message);
    }
}
