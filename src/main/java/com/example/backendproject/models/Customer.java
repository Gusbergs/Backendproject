package com.example.backendproject.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {


    @Id
    @GeneratedValue
    long id;

    String name;

    String email;
    @OneToMany(mappedBy = "customer",  fetch = FetchType.EAGER)
    private List<Booking> bookings;

    public Customer(String name, String email){
        this.name = name;
        this.email = email;

    }
    public Customer(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;

    }

}
