package com.example.howdynewsfeed.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String poruka,Long id){
        super("Could not found "+poruka+" with id "+id);
    }
}
