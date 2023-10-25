package com.example.words.services.Impl;

import com.example.words.models.Word;
import com.example.words.repositories.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class BashkirianWordService extends AbstractWordService {


    public BashkirianWordService(WordRepository wordRepository) {
        super(wordRepository);
    }

    @Override
    public String getLanguage(){
        return "Bashkirian";
    }
}
