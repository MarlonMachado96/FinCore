package com.fincore.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank(message="Usuário é obrigatório") String username, @NotBlank(message="Senha é obrigatória") String password) {
    
}
