package com.fincore.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fincore.config.TokenConfig;
import com.fincore.dtos.LoginRequest;
import com.fincore.dtos.LoginResponse;
import com.fincore.dtos.RegisterRequest;
import com.fincore.dtos.RegisterResponse;
import com.fincore.entities.User;
import com.fincore.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/auth")
public class AuthController {
    
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenConfig tokenConfig;

    public AuthController(UserRepository userRepository, AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, TokenConfig tokenConfig) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        UsernamePasswordAuthenticationToken passAndKey = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        Authentication authentication = authenticationManager.authenticate(passAndKey);
        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.ok().body(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request){
        String encodedPassword = passwordEncoder.encode(request.password());
        User newUser = new User(null, request.username(), encodedPassword, "user");
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(newUser.getUsername()));

    }

}
