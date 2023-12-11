package com.jwt.service;

import com.jwt.dao.RoleRepo;
import com.jwt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role addRole(Role role){
        return roleRepo.save(role);
    }
}
