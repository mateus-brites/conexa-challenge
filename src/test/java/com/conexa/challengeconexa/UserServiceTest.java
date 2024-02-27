package com.conexa.challengeconexa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.conexa.challengeconexa.modules.user.dtos.CreateUserDTO;
import com.conexa.challengeconexa.modules.user.entities.UserEntity;
import com.conexa.challengeconexa.modules.user.repositories.UserRepository;
import com.conexa.challengeconexa.modules.user.services.UserService;

import jakarta.persistence.EntityExistsException;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Shoulde create a new user with sucess")
    public void createUserSucess(){
        UserEntity userEntity = new UserEntity();

        CreateUserDTO userDTO = new CreateUserDTO(
            "teste@gmail.com", 
            "senha",
            "senha",
            "dermatologista",
            "571.988.230-84",
            "12/11/2000",
            "(19) 99887-7665"
            );

        BeanUtils.copyProperties(userDTO, userEntity);

        userService.create(userEntity);

        var size = userRepository.findAll().size();

        Assertions.assertEquals(1, size);

    }

    @Test
    @DisplayName("Should not be able to create a new user if email is in use")
    public void createUserErrorIfEmailExistsInDb(){
        UserEntity userEntity = new UserEntity();

        CreateUserDTO userDTO = new CreateUserDTO(
            "teste2@gmail.com", 
            "senha",
            "senha",
            "dermatologista",
            "571.988.230-84",
            "12/11/2000",
            "(19) 99887-7665"
            );

        BeanUtils.copyProperties(userDTO, userEntity);

        userService.create(userEntity);

        Assertions.assertThrows(EntityExistsException.class,() -> userService.create(userEntity));

    }
}
