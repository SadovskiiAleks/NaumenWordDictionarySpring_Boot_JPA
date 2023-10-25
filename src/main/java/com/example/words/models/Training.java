package com.example.words.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Training {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Training(String name) {
        this.name = name;
    }
}
