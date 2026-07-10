package com.fincore.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
        try{
            Optional<User> user = repository.findById(id);
            return user.get();
        }catch(IOException e){
            throw new IOException(e.getMessage());
        }
        
    }
}
