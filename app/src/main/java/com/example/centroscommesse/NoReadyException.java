package com.example.centroscommesse;

public class NoReadyException extends Exception {
    public NoReadyException(String errorMessage) {
        super(errorMessage);
    }
}

