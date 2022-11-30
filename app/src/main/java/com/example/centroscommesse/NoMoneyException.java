package com.example.centroscommesse;

public class NoMoneyException extends Exception {
    public NoMoneyException(String errorMessage) {
        super(errorMessage);
    }
}