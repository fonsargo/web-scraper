package ru.spbsu.apmath.webscraper.data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Афонин Сергей (hrundelb@yandex.ru)
 * Date: 07.08.2014
 * Time: 14:22
 */
public class WebPage {
    private String url;
    private List<Word> words;
    private long scrapTime;
    private long processTime;
    private int numberOfCharacters;
    private String errorMessage;

    public WebPage(String url, List<Word> words) {
        this.url = url;
        this.words = words;
        this.numberOfCharacters = 0;
    }

    public String getUrl() {
        return url;
    }

    public List<Word> getWords() {
        return words;
    }

    public void addToNumberOfCharacters(int addition) {
        this.numberOfCharacters += addition;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }

    public void setScrapTime(long scrapTime) {
        this.scrapTime = scrapTime;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void add(WebPage anotherPage) {
        this.scrapTime += anotherPage.scrapTime;
        this.processTime += anotherPage.processTime;
        this.numberOfCharacters += anotherPage.numberOfCharacters;
        if (this.words.size() != anotherPage.words.size()) {
            throw new IllegalArgumentException("Can't adding pages with different number of words");
        }
        for (int i = 0; i < words.size(); i++) {
            words.get(i).add(anotherPage.words.get(i));
        }
    }

    public void print(boolean printTimeSpent, boolean printNumberOfCharacters, boolean printNumberOfWords,
                      boolean printSentences) {
        System.out.println(url);
        if (errorMessage != null) {
            System.out.println(String.format("\terror: %s", errorMessage));
        } else {
            if (printTimeSpent) {
                System.out.println(String.format("\tscrap time: %s ms", scrapTime));
                System.out.println(String.format("\tprocess time: %s ms", processTime));
            }
            if (printNumberOfCharacters) {
                System.out.println(String.format("\tnumber of characters: %s", numberOfCharacters));
            }
            System.out.println("\twords:");
            for (Word word : words) {
                word.print(printNumberOfWords, printSentences);
            }
            System.out.println();
        }
    }
}
