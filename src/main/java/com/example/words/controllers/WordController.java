package com.example.words.controllers;

import com.example.words.models.Training;
import com.example.words.models.Word;
import com.example.words.models.WordTrainingLink;
import com.example.words.services.TrainingService;
import com.example.words.services.TrainingWordService;
import com.example.words.services.WordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WordController {

    @Value("${project.language}")
    private String language;

    private final List<WordServices> wordServices;
    private final Map<String, WordServices> wordServicesMap = new HashMap<>();
    public final TrainingService trainingService;
    public final TrainingWordService trainingWordService;

    @Autowired
    public WordController(List<WordServices> wordServices, TrainingService trainingService, TrainingWordService trainingWordService) {
        this.wordServices = wordServices;
        this.trainingService = trainingService;
        this.trainingWordService = trainingWordService;
        wordServices.forEach(wordService -> {
            wordServicesMap.put(wordService.getLanguage(), wordService);
        });

    }

    @GetMapping(value = "/allWords", params = {"language"})
    public List<Word> getAllWordsWithParam(@RequestParam(value = "language") String language) {
        return wordServicesMap.get(language).getAllWords();
    }

    @GetMapping("/allWords")
    public List<Word> getAllWords() {
        return wordServicesMap.get(this.language).getAllWords();
    }

    @GetMapping("/addWord/{word}/{translation}/{language}")
    public List<Word> addWord(@PathVariable String word, @PathVariable String translation,
                              @PathVariable String language) {
        Word newWord = new Word(word, translation, language);
        getService().saveWord(newWord);
        return getAllWords();
    }

    private WordServices getService() {
        return wordServicesMap.get(language);
    }

    @GetMapping("addTraining/{training}")
    public List<Training> addTraining(@PathVariable String training) {
        trainingService.saveTraining(training);
        return getAllTraining();
    }

    @GetMapping("/allTrainings")
    private List<Training> getAllTraining() {
        return trainingService.getAllTrainings();
    }

    @GetMapping("/addWordToTraining/{wordId}/{trainingId}")
    public WordTrainingLink addWordTrainingLink(@PathVariable Long wordId, @PathVariable Long trainingId) {
        Word word = getService().getWordById(wordId);
        Training training = trainingService.getById(trainingId);
        return trainingWordService.addWordToTraining(word, training);
    }
}

