package com.conexa.challengeconexa.modules.tokensBlackList.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conexa.challengeconexa.modules.tokensBlackList.dto.CreateTokenBlackListDTO;
import com.conexa.challengeconexa.modules.tokensBlackList.entities.TokenBlackListEntity;
import com.conexa.challengeconexa.modules.tokensBlackList.services.TokensBlackListService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class TokensBlackListController {
    final TokensBlackListService tokensBlackListService;

    public TokensBlackListController(TokensBlackListService tokensBlackListService) {
        this.tokensBlackListService = tokensBlackListService;
    }
    

    @PostMapping("/logoff")
    public ResponseEntity<TokenBlackListEntity> create(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        var token = authHeader.replace("Bearer ", "");

        TokenBlackListEntity tokenBlackListEntity = new TokenBlackListEntity();
        CreateTokenBlackListDTO dto = new CreateTokenBlackListDTO(token);

        BeanUtils.copyProperties(dto, tokenBlackListEntity);

        TokenBlackListEntity tokenBlackList = tokensBlackListService.create(tokenBlackListEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(tokenBlackList);
    }
}
