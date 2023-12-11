package com.jwt.service;

import com.jwt.dao.UserRepo;
import com.jwt.entity.JwtRequest;
import com.jwt.entity.JwtResponse;
import com.jwt.entity.User;
import com.jwt.helper.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepo userRepo;
    public JwtResponse generateToken(JwtRequest jwtRequest){
        String userName = jwtRequest.getUserName();
        String password = jwtRequest.getPassword();
        authenticate(userName,password);
        UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
        String token = jwtUtil.generateToken(userDetails);
        User user = userRepo.findById(userName).get();
        return new JwtResponse(user,token);
    }

    public void authenticate(String userName,String password){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
        }catch (DisabledException e){
            throw new DisabledException("User is disabled");
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Bad credentials");
        }

    }
}
