package com.example.backendproject.models;


import jakarta.persistence.*;
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
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Booking> bookings;

    public Customer(String name, String email){
        this.name = name;
        this.email = email;

    }


}
