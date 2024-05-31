package com.example.backendproject.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    private String username;
    private String password;
    private boolean enabled;
    private String resetToken;
    private LocalDateTime expiringDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    public User(String email, String password, Collection roles) {
    }


}