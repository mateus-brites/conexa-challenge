package com.conexa.challengeconexa.modules.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conexa.challengeconexa.modules.security.services.TokenService;
import com.conexa.challengeconexa.modules.user.dtos.AuthenticationDTO;
import com.conexa.challengeconexa.modules.user.dtos.LoginResponseDTO;
import com.conexa.challengeconexa.modules.user.entities.UserEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO request){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new LoginResponseDTO(token));
    }
}
