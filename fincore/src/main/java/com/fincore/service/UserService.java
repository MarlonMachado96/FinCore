package com.fincore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fincore.dto.UserRequestDTO;
import com.fincore.dto.UserResponseDTO;
import com.fincore.entitie.User;
import com.fincore.entitie.enums.UserRole;
import com.fincore.exception.DuplicateEmailException;
import com.fincore.exception.UserNotFoundException;
import com.fincore.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDTO findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return toResponse(user);
    }

    public UserResponseDTO create(UserRequestDTO request) {
        if (repository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(request.email());
        }

        User user = new User();
        user.setNome(request.nome());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role() != null ? request.role() : UserRole.USER);

        return toResponse(repository.save(user));
    }

    public UserResponseDTO update(Long id, UserRequestDTO request) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (request.nome() != null && !request.nome().isBlank()) {
            existingUser.setNome(request.nome());
        }

        if (request.email() != null && !request.email().isBlank()) {
            if (!request.email().equals(existingUser.getEmail()) && repository.existsByEmail(request.email())) {
                throw new DuplicateEmailException(request.email());
            }
            existingUser.setEmail(request.email());
        }

        if (request.password() != null && !request.password().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(request.password()));
        }

        if (request.role() != null) {
            existingUser.setRole(request.role());
        }

        return toResponse(repository.save(existingUser));
    }

    public void delete(Long id) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        repository.delete(existingUser);
    }

    private UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(user.getId(), user.getNome(), user.getEmail(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
