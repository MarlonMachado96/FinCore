package com.fincore.dto;

import com.fincore.entitie.enums.UserRole;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank(message="Usuário é obrigatório") String username, @NotBlank(message="Senha é obrigatória") String password, UserRole role) {
    
}
