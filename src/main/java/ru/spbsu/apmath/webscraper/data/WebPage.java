package ru.spbsu.apmath.webscraper.data;

import java.net.URL;
import java.util.List;

import static ru.spbsu.apmath.webscraper.PageUtils.getWordsString;

/**
 * Created by IntelliJ IDEA.
 * User: Афонин Сергей (hrundelb@yandex.ru)
 * Date: 07.08.2014
 * Time: 14:22
 */
public class WebPage {
    private URL url;
    private List<Word> words;
    private long scrapTime;
    private long processTime;
    private int numberOfCharacters;
    private String errorMessage;

    public WebPage(URL url, List<Word> words) {
        this.url = url;
        this.words = words;
        this.numberOfCharacters = 0;
    }

    public URL getUrl() {
        return url;
    }

    public List<Word> getWords() {
        return words;
    }

    public long getScrapTime() {
        return scrapTime;
    }

    public long getProcessTime() {
        return processTime;
    }

    public int getNumberOfCharacters() {
        return numberOfCharacters;
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

    @Override
    public String toString() {
        if (errorMessage != null) {
            return String.format("%s\n\terror: %s\n", url, errorMessage);
        } else {
            return String.format("%s\n\tscrap time: %s ms\n\tprocess time: %s ms\n\tnumber of characters: %s\n\twords:\n%s",
                    url, scrapTime, processTime, numberOfCharacters, getWordsString(words));
        }
    }
}
