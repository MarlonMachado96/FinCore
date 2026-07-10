package com.fincore.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fincore.repositories.UserRepository;

@Service
public class AuthConfig implements UserDetailsService{

    private final UserRepository userRepository;

    public AuthConfig(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
    }
    
}
