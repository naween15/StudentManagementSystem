package com.example.vidhyarthi.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
