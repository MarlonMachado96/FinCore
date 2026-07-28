package com.fincore.dto;

import java.time.LocalDateTime;

import com.fincore.entitie.enums.UserRole;

public record UserResponseDTO(Long id, String nome, String email, UserRole role, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
