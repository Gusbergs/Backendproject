package com.example.backendproject.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {


    @Id
    @GeneratedValue
    long id;

    String name;

    String email;

    public Customer(String name, String email){
        this.name = name;
        this.email = email;

    }

}
