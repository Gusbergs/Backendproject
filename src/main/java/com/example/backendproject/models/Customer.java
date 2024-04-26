package com.example.backendproject.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Customer {


    @Id
    @GeneratedValue
    long id;

    String name;

    String email;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookingList;

    public Customer(String name, String email){
        this.name = name;
        this.email = email;

    }

}
