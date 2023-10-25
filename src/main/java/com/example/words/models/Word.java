package com.example.words.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue
    private Long id;
    private String word;
    private String translation;
    private String language;

    public Word(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public Word(String word, String translation, String language) {
        this.word = word;
        this.translation = translation;
        this.language = language;
    }


}
