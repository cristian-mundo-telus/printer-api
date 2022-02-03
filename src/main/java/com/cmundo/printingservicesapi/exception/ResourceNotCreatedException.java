package com.cmundo.printingservicesapi.exception;

public class ResourceNotCreatedException extends RuntimeException {
    private String message;

    public ResourceNotCreatedException(String message) {
        super(message);
        this.message = message;
    }
}
