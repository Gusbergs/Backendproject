package com.example.backendproject.service;

import com.example.backendproject.dto.UserDtoMini;
import com.example.backendproject.models.Role;
import com.example.backendproject.models.User;
import com.example.backendproject.repo.RoleRepo;
import com.example.backendproject.repo.UserRepo;
import com.example.backendproject.security.ConcreteUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @BeforeEach
    void setUp() {

        userRepo.deleteAll();
        roleRepo.deleteAll();


        Role roleUser = Role.builder().name("USER").build();
        roleRepo.save(roleUser);

        User user = User.builder()
                .username("test@example.com")
                .password(new BCryptPasswordEncoder().encode("password"))
                .enabled(true)
                .roles(List.of(roleUser))
                .build();
        userRepo.save(user);
    }

    @Test
    void testLoadUserByUsername() {
        ConcreteUserDetails userDetails = (ConcreteUserDetails) userService.loadUserByUsername("test@example.com");
        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonexistent@example.com"));
    }

    @Test
    void testCheckIfUserExist() {
        assertTrue(userService.checkIfUserExist("test@example.com"));
        assertFalse(userService.checkIfUserExist("nonexistent@example.com"));
    }

    @Test
    void testUserDtoMini() {
        User user = userRepo.getUserByUsername("test@example.com");
        UserDtoMini userDtoMini = userService.userDtoMini(user);
        assertEquals("test@example.com", userDtoMini.getUsername());
        assertNotNull(userDtoMini.getRoles());
        assertTrue(userDtoMini.getRoles().contains("USER"));
    }

    @Test
    void testAddUserWithPassword() {
        userService.addUserWithPassword("newuser@example.com", "newpassword", "USER");
        User newUser = userRepo.getUserByUsername("newuser@example.com");
        assertNotNull(newUser);
        assertTrue(new BCryptPasswordEncoder().matches("newpassword", newUser.getPassword()));
        assertTrue(newUser.getRoles().stream().anyMatch(role -> role.getName().equals("USER")));
    }

    @Test
    void testUserDtoConvertToIterabel() {
        Iterable<User> users = userRepo.findAll();
        List<UserDtoMini> userDtoMinis = userService.userDtoConvertToIterabel(users);
        assertNotNull(userDtoMinis);
        assertEquals(1, userDtoMinis.size());
        assertEquals("test@example.com", userDtoMinis.get(0).getUsername());
    }
}
