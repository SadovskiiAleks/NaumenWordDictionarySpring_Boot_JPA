package com.example.words.services;

import com.example.words.models.Word;

import java.util.List;

public interface WordServices {
    List<Word> getAllWords();
    void saveWord(String word, String translation);
    String getLanguage();
    Word getWordById(Long id);

    void saveWord(Word word);
}
