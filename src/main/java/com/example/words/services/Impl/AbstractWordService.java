package com.example.words.services.Impl;

import com.example.words.models.Word;
import com.example.words.repositories.WordRepository;
import com.example.words.services.WordServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class AbstractWordService implements WordServices {

    private final WordRepository wordRepository;

    @Autowired
    protected AbstractWordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public List<Word> getAllWords() {
        List<Word> result = new ArrayList<>();
        wordRepository.findAll().forEach(result::add);

        return result.stream().filter(word ->
            word.getLanguage().equals(getLanguage())).collect(Collectors.toList());
    }

    @Override
    public void saveWord(String word, String translation) {
        wordRepository.save(new Word(word,translation));
    }

    @Override
    public Word getWordById(Long id){
        return wordRepository.findById(id).get();
    }

    @Override
    public void saveWord(Word word) {
        wordRepository.save(word);
    }

    @Override
    public String getLanguage(){
        return null;
    }


}
