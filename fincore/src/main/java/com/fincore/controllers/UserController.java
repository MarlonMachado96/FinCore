package com.fincore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fincore.entities.User;
import com.fincore.services.UserService;


@RestController
@RequestMapping(value="/users")
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

   @PostMapping("/register")
   public ResponseEntity<User> registerNewUser(@RequestBody User newUser) {
       return ResponseEntity.status(HttpStatus.CREATED).body(service.insertUser(newUser));
   }
   
}
