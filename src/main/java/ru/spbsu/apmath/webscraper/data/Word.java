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

    public void add(Word anotherWord) {
        this.count += anotherWord.count;
        this.sentences.addAll(anotherWord.sentences);
    }

    public void print(boolean printNumberOfWords, boolean printSentences) {
        System.out.println(String.format("\t%s", word));
        if (printNumberOfWords) {
            System.out.println(String.format("\t\tcount: %s", count));
        }
        if (printSentences) {
            System.out.println(String.format("\t\tsentences (%s):", sentences.size()));
            for (String sentence : sentences) {
                System.out.println(String.format("\t\t\t%s", sentence));
            }
        }
    }
}
