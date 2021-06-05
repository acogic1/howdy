package com.example.howdyuser.ExceptionClasses;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(String poruka,Long id){
        super("Could not found "+poruka+" with id "+id);
    }
}
