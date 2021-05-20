package com.example.howdynewsfeed.Exceptions;

public class InvalidUsernameOrPasswordException extends  RuntimeException{

    public InvalidUsernameOrPasswordException() {
    }

    public InvalidUsernameOrPasswordException(String message) {
        super(message);
    }

    public InvalidUsernameOrPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
