package com.example.backendproject.repo;

import com.example.backendproject.models.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoleRepo extends CrudRepository<Role, UUID> {

    Role findByName(String name);
}