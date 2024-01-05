package com.api.usuarios.web.exception;

import org.springframework.validation.FieldError;

public record Message(String field, String msg) {
    public Message(FieldError e){ this(e.getField(), e.getDefaultMessage()); }
}
