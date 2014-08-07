package ru.spbsu.apmath.webscraper.data;

import java.net.URL;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Афонин Сергей (hrundelb@yandex.ru)
 * Date: 07.08.2014
 * Time: 14:22
 */
public class WebPage {
    private URL url;
    private List<Word> words;
    private long timeSpent;
    private int numberOfCharacters;

    public WebPage(URL url, List<Word> words) {
        this.url = url;
        this.words = words;
    }

    public URL getUrl() {
        return url;
    }

    public List<Word> getWords() {
        return words;
    }
}
