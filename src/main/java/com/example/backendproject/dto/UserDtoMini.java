package com.example.backendproject.dto;


import com.example.backendproject.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoMini {
     UUID id;

     String username;
     String password;

     Collection<String> roles;
}
