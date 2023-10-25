package com.example.words.services;

import com.example.words.models.Training;
import com.example.words.repositories.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService {
    private final TrainingRepository repository;

    public void saveTraining(String trainingName){
        Training training = new Training(trainingName);
        repository.save(training);
    }

    public Training getById(Long id){
        return repository.findById(id).get();
    }

    public List<Training> getAllTrainings(){
        List<Training> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }
}
