package com.example.words.repositories;

import com.example.words.models.Training;
import com.example.words.models.Word;
import com.example.words.models.WordTrainingLink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordTrainingLinkRepository extends CrudRepository<WordTrainingLink, Long> {

    List<WordTrainingLink> findByTraining(Training training);
    List<WordTrainingLink> findByLastStudyDayIsNull();
    List<WordTrainingLink> findByWordAndTraining(Word word, Training training);
}
