package com.fincore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fincore.entities.User;
import com.fincore.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        return repository.findById(id)
        .orElseThrow(() ->
            new RuntimeException("Usuário não encontrado"));
        
    }

    public User insertUser(User newUser){
        return repository.save(newUser);
    }
}
