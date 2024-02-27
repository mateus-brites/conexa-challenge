package com.conexa.challengeconexa.modules.user.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conexa.challengeconexa.modules.user.dtos.CreateUserDTO;
import com.conexa.challengeconexa.modules.user.entities.UserEntity;
import com.conexa.challengeconexa.modules.user.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test(){
        return "";
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> create(@RequestBody @Valid CreateUserDTO request){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(request, userEntity);
        System.out.println("AQUI");

        UserEntity newUser = userService.create(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
