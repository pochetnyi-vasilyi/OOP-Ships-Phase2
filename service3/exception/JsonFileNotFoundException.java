package com.service3.exception;

public class JsonFileNotFoundException extends RuntimeException {
    public JsonFileNotFoundException (String message) {
        super(message);
    }
}
