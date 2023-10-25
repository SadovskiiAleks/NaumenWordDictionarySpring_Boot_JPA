package com.example.words.services;

import com.example.words.models.Training;
import com.example.words.models.Word;
import com.example.words.models.WordTrainingLink;
import com.example.words.repositories.WordTrainingLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingWordService {
    private final WordTrainingLinkRepository wordTrainingLinkRepository;
    private final TrainingService trainingService;

    public WordTrainingLink addWordToTraining(Word word, Training training) {
        WordTrainingLink wordTrainingLink = new WordTrainingLink(word, training);
        wordTrainingLinkRepository.save(wordTrainingLink);
        return wordTrainingLink;
    }

    public List<Word> getWordByTrainingName(String trainingName) {
        Training training = new Training(trainingName);
        List<WordTrainingLink> wordTrainingLinks = wordTrainingLinkRepository.findByTraining(training);
        return wordTrainingLinks.stream().map(wordTrainingLink -> wordTrainingLink.getWord()).collect(Collectors.toList());
    }

    public List<Word> getWordByTrainingId(Long trainingId) {
        Training training = trainingService.getById(trainingId);
        List<WordTrainingLink> wordTrainingLinks = wordTrainingLinkRepository.findByTraining(training);
        return wordTrainingLinks.stream().map(wordTrainingLink -> wordTrainingLink.getWord()).collect(Collectors.toList());
    }

    public List<Word> getWordByTrainingIdAndLanguage(Long trainingId, String language) {
        Training training = trainingService.getById(trainingId);
        List<WordTrainingLink> wordTrainingLinks = wordTrainingLinkRepository.findByTraining(training);
        return wordTrainingLinks.stream().map(wordTrainingLink -> wordTrainingLink.getWord())
                .filter(word -> word.getLanguage().equals(language))
                .collect(Collectors.toList());
    }

    public List<Word> getEnglishWordByTrainingId(Long trainingId) {
        return getWordByTrainingIdAndLanguage(trainingId, "english");
    }

    public List<Word> getBaskirianWordByTrainingId(Long trainingId) {
        return getWordByTrainingIdAndLanguage(trainingId, "bashkirian");
    }
}
