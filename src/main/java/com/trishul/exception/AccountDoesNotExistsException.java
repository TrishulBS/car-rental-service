package com.trishul.exception;

public class AccountDoesNotExistsException extends Exception {
    public AccountDoesNotExistsException(String message) {
        super(message);
    }
}