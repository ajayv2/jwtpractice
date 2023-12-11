package com.jwt.controller;

import com.jwt.entity.JwtRequest;
import com.jwt.entity.JwtResponse;
import com.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtController {

    @Autowired
    private JwtService jwtService;
    @PostMapping("/authenticate")
    public JwtResponse generateToken(@RequestBody JwtRequest jwtRequest){
        System.out.println("request arrived for token generation");
        System.out.println("jwt request is "+ jwtRequest);
        return jwtService.generateToken(jwtRequest);
    }
}
