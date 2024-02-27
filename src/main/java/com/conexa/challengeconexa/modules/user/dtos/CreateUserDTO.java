package com.conexa.challengeconexa.modules.user.dtos;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
    @NotBlank @Email String email,
    @NotBlank String senha,
    @NotBlank String confirmacaoSenha,
    @NotBlank String especialidade,
    @NotBlank @CPF String cpf,
    @NotBlank String dataNascimento,
    @NotBlank String telefone
) {
    
}
