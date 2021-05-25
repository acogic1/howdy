package com.example.howdynewsfeed.Exceptions;

public class NoPermisionException extends  RuntimeException{
    public NoPermisionException() {
    }

    public NoPermisionException(String message) {
        super(message);
    }

    public NoPermisionException(String message, Throwable cause) {
        super(message, cause);
    }
}
