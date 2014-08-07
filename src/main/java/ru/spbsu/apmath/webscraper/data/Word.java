package ru.spbsu.apmath.webscraper.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Афонин Сергей (hrundelb@yandex.ru)
 * Date: 07.08.2014
 * Time: 14:11
 */
public class Word {
    private String word;
    private int count;
    private List<String> sentences;

    public Word(String word) {
        this.word = word;
        this.count = 0;
        this.sentences = new ArrayList<String>();
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public void increaseCount() {
        this.count++;
    }

    public void addSentence(String sentence) {
        this.sentences.add(sentence);
    }
}
