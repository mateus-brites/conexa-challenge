package com.conexa.challengeconexa.modules.tokensBlackList.services;

import org.springframework.stereotype.Service;

import com.conexa.challengeconexa.modules.tokensBlackList.entities.TokenBlackListEntity;
import com.conexa.challengeconexa.modules.tokensBlackList.repositories.TokenBlackListRepository;


@Service
public class TokensBlackListService {
    final TokenBlackListRepository tokenBlackListRepository;

    public TokensBlackListService(TokenBlackListRepository tokenBlackListRepository) {
        this.tokenBlackListRepository = tokenBlackListRepository;
    }

    public TokenBlackListEntity create(TokenBlackListEntity token) {
        return tokenBlackListRepository.save(token);
    }

    public Boolean tokenIsInBlackList(TokenBlackListEntity token) {
        if(tokenBlackListRepository.findByToken(token.getToken()) != null) {
            return true;
        } else {
            return false;
        }
    }
}
