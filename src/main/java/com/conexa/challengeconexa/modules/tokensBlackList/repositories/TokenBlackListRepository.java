package com.conexa.challengeconexa.modules.tokensBlackList.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conexa.challengeconexa.modules.tokensBlackList.entities.TokenBlackListEntity;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackListEntity, UUID> {
    TokenBlackListEntity findByToken(String token);
}
