package com.jwt.service;

import com.jwt.dao.RoleRepo;
import com.jwt.dao.UserRepo;
import com.jwt.entity.Role;
import com.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
    public User registerNewUser(User user){
        System.out.println("User is "+user);
        Role role = roleRepo.findById("User").get();
        Set<Role> roles = new HashSet<>();
        System.out.println("ROles are for user "+role);
        roles.add(role);
        user.setRole(roles);
        user.setUsername(user.getUsername());
        return userRepo.save(user);
    }

    public void initRolesAndUsers(){
        Role adminRole = new Role("Admin","This is for admin role");
        roleRepo.save(adminRole);
        Role userRole = new Role("User","This is for user role");
        roleRepo.save(userRole);

        User adminUser = new User();
        adminUser.setFirstName("Admin");
        adminUser.setLastName(("Admin"));
        adminUser.setPassword("admin123");
        adminUser.setUsername("admin123");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        adminUser.setRole(adminRoles);
        userRepo.save(adminUser);
    }
}
