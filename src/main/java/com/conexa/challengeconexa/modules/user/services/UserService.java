package com.conexa.challengeconexa.modules.user.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.conexa.challengeconexa.modules.user.entities.UserEntity;
import com.conexa.challengeconexa.modules.user.repositories.UserRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserEntity create(UserEntity userEntity) {
        var userAlreadyExist = userRepository.findByEmail(userEntity.getEmail());

        if(userAlreadyExist != null) {
            throw new EntityExistsException("Email already exist");
        }
        String senha = userEntity.getSenha();
        String hash = new BCryptPasswordEncoder().encode(senha);

        userEntity.setSenha(hash);
        return this.userRepository.save(userEntity);
    }
}
