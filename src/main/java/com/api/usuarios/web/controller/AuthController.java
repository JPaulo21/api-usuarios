package com.api.usuarios.web.controller;

import com.api.usuarios.web.dto.UserDTO;
import com.api.usuarios.web.exception.Message;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    //Gerenciador de autenticação, esse objeto que de fato faz a autenticação, sendo injetado pelo Spring Security
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> autenticar(@Valid @RequestBody UserDTO login){

        try{
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(login.username(), login.password());
            // O método authenticate irá usar a implementação de UserDetails, e utilizar o método loadUserByUsername, para autenticar
            authenticationManager.authenticate(authenticationToken);
        } catch(AuthenticationException e){
            log.error("Erro de autenticação: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Message("User", e.getMessage()));
        }

        return ResponseEntity.accepted().build();
    }

}
