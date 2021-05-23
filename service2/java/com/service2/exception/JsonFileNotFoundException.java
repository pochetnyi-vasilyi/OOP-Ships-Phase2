package com.service2.exception;

public class JsonFileNotFoundException extends RuntimeException {
    public JsonFileNotFoundException (String message) {
        super(message);
    }
}
