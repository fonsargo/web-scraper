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

    public void increaseCount() {
        this.count++;
    }

    public void addSentence(String sentence) {
        this.sentences.add(sentence);
    }

    @Override
    public String toString() {
        String s = "";
        for (String sentence: sentences) {
            s += String.format("\t\t\t%s\n", sentence);
        }
        return String.format("\t%s\n\t\tcount: %s\n\t\tsentences (%s):\n%s", word, count, sentences.size(), s);
    }
}
