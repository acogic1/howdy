package com.example.howdyMessagesFollowersFollowing.ExceptionClasses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(String poruka,Long id){
        super("Could not found "+poruka+" with id "+id);
    }
}
