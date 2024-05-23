package com.example.backendproject.security;

import com.example.backendproject.models.Role;
import com.example.backendproject.models.User;
import com.example.backendproject.repo.RoleRepo;
import com.example.backendproject.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDataSeeder {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    public void Seed(){
        if (roleRepo.findByName("Admin") == null) {
            addRole("Admin");
        }
        if (roleRepo.findByName("Receptionist") == null) {
            addRole("Receptionist");
        }
        if(userRepo.getUserByUsername("grupp5@admin.com") == null){
            addUser("grupp5@admin.com","Admin");
        }
        if(userRepo.getUserByUsername("grupp5@receptionist.com") == null){
            addUser("grupp5@receptionist.com","Receptionist");
        }
    }

    private void addUser(String mail, String group) {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleRepo.findByName(group));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("123");
        User user = User.builder().enabled(true).password(hash).username(mail).roles(roles).build();
        userRepo.save(user);
    }

    private void addRole(String name) {
        Role role = new Role();
        roleRepo.save(Role.builder().name(name).build());
    }

}