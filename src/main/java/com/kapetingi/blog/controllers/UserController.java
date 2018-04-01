package com.kapetingi.blog.controllers;

import com.kapetingi.blog.dto.UserRegistration;
import com.kapetingi.blog.entities.Role;
import com.kapetingi.blog.entities.User;
import com.kapetingi.blog.repositories.UserRepository;
import com.kapetingi.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "register")
    public String register(@RequestBody UserRegistration userRegistration) {
        if(!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation())){
            return "password dont match";
        } else if(userService.getUser(userRegistration.getUserName()) != null){
            return "user already exist";
        }

        userService.save(new User(userRegistration.getUserName(),
                userRegistration.getPassword(),
                Arrays.asList(new Role("USER"))));
        return "user created";
    }


    @GetMapping(value = "users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    @GetMapping(value ="/getUsername")
    public String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
