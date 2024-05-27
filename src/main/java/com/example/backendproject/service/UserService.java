package com.example.backendproject.service;

import com.example.backendproject.dto.UserDtoMini;
import com.example.backendproject.models.Role;
import com.example.backendproject.models.User;
import com.example.backendproject.repo.RoleRepo;
import com.example.backendproject.repo.UserRepo;
import com.example.backendproject.security.ConcreteUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepo.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new ConcreteUserDetails(user);
    }




    public boolean checkIfUserExist(String email){
        return userRepo.getUserByUsername(email) != null;
    }

    public UserDtoMini userDtoMini(User user) {
        Collection<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        return UserDtoMini.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roleNames)
                .build();
    }
    public List<UserDtoMini> userDtoConvertToIterabel(Iterable<User> users){
        return StreamSupport.stream(users.spliterator(),false)
                .map(this::userDtoMini)
                .collect(Collectors.toList());
    }

    public void addUserWithPassword(String mail, String password, String groupName) {
        Role role = roleRepo.findByName(groupName);
        if (role == null) {
            throw new RuntimeException("Role not found for name: " + groupName);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(password);

        User user = User.builder()
                .enabled(true)
                .password(hash)
                .username(mail)
                .build();
        if (user.getRoles() == null) {
            user.setRoles(new ArrayList<>());
        } else {
            for (Role existingRole : user.getRoles()) {
                if (existingRole.getName().equals(groupName)) {

                    return;
                }
            }
        }
        user.getRoles().add(role);
        roleRepo.save(role);
        userRepo.save(user);
    }



}