package com.example.centroscommesse;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String errorMessage) {
        super(errorMessage);
    }
}
