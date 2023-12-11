package com.jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {

    @GetMapping("/login")
    @PreAuthorize("hasAnyRole('User','Admin')")
    public String login(){
        System.out.println("This is login controller");
        return "This is login for all users";
    }

    @GetMapping("/registration")
    @PreAuthorize("hasRole('Admin')")
    public String registration(){
        return "This is for admin users";
    }
}
