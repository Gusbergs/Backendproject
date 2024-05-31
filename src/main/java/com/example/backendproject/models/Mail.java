package com.example.backendproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mail {

    @Id
    @GeneratedValue
    Long id;

    String subject;

    String text;

    public Mail(String subject, String text) {
        this.subject = subject;
        this.text = text;
    }
}
