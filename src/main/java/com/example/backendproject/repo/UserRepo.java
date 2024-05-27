package com.example.backendproject.repo;

import com.example.backendproject.models.Customer;
import com.example.backendproject.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends CrudRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    Optional<User> findByUsername(String email);


}