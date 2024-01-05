package com.api.usuarios.web.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity argumentoInvalido(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(e.getFieldErrors()
                .stream()
                .map(Message::new)
                .toList());
    }

}
