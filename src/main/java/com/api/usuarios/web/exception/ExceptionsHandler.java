package com.api.usuarios.web.exception;

import com.api.usuarios.exception.UsernameUniqueViolationException;
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

    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity usernameJaCadastrado(UsernameUniqueViolationException e){
        return ResponseEntity.unprocessableEntity()
                .body(new Message("username",e.getMessage()));

    }
}
