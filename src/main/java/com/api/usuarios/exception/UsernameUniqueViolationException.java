package com.api.usuarios.exception;

public class UsernameUniqueViolationException extends RuntimeException {
    public UsernameUniqueViolationException(String msg) {
        super(msg);
    }
}
