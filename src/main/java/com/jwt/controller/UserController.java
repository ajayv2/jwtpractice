package com.jwt.controller;

import com.jwt.entity.User;
import com.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUsers();
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public User createNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    }
}
