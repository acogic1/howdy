package com.example.howdynewsfeed.Exceptions;

public class EntityAreadyExistsException extends RuntimeException {

    public EntityAreadyExistsException() {
    }

    public EntityAreadyExistsException(String message) {
        super(message);
    }

}
