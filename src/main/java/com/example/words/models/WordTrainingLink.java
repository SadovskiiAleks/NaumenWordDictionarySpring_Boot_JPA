package com.example.words.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class WordTrainingLink {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Word word;

    @ManyToOne
    private Training training;

    private Date lastStudyDay = null;

    private int repeatDays = -1;

    public WordTrainingLink(Word word, Training training) {
        this.word = word;
        this.training = training;
    }
}
