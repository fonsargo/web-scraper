package ru.spbsu.apmath.webscraper;

import ru.spbsu.apmath.webscraper.data.WebPage;
import ru.spbsu.apmath.webscraper.data.Word;

import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static ru.spbsu.apmath.webscraper.PageUtils.findWordsAndSentences;
import static ru.spbsu.apmath.webscraper.PageUtils.getHtmlDocument;
import static ru.spbsu.apmath.webscraper.PageUtils.getWordsString;

/**
 * Created by IntelliJ IDEA.
 * User: Афонин Сергей (hrundelb@yandex.ru)
 * Date: 07.08.2014
 * Time: 14:36
 */
public class Scraper {
    private List<WebPage> webPages;
    private List<String> originalWords;

    public Scraper(URL url, List<String> words) {
        originalWords = words;
        List<Word> wordList = getWords(words);
        webPages = new ArrayList<WebPage>();
        webPages.add(new WebPage(url, wordList));
    }

    public Scraper(List<URL> urls, List<String> words) {
        originalWords = words;
        webPages = new ArrayList<WebPage>();
        for (URL url: urls) {
            List<Word> wordList = getWords(words);
            webPages.add(new WebPage(url, wordList));
        }
    }

    public void scrap() {
        for (WebPage webPage : webPages) {
            processPage(webPage);
        }
    }

    public void printResults() {
        long totalScrapTime = 0;
        long totalProcessTime = 0;
        int totalNumberOfCharacters = 0;
        List<Word> wordList = getWords(originalWords);
        for (WebPage webPage: webPages) {
            System.out.println(webPage.toString());
            totalScrapTime += webPage.getScrapTime();
            totalProcessTime += webPage.getProcessTime();
            totalNumberOfCharacters += webPage.getNumberOfCharacters();
            for (int i = 0; i < webPage.getWords().size(); i++) {
                wordList.get(i).increaseCount(webPage.getWords().get(i).getCount());
                wordList.get(i).addSentences(webPage.getWords().get(i).getSentences());
            }
        }
        System.out.println(String.format("TOTAL\n\tscrap time: %s ms\n\tprocess time: %s ms\n\tnumber of characters: %s" +
                        "\n\twords:\n%s", totalScrapTime, totalProcessTime, totalNumberOfCharacters, getWordsString(wordList)));
    }

    private void processPage(WebPage webPage) {
        long scrapTime = -System.currentTimeMillis();
        HTMLDocument doc = null;
        try {
            doc = getHtmlDocument(webPage.getUrl());
        } catch (IOException e) {
            webPage.setErrorMessage(String.format("%s: %s", e.getClass().getName(), e.getMessage()));
            return;
        } catch (BadLocationException e) {
            webPage.setErrorMessage(String.format("%s: %s", e.getClass().getName(), e.getMessage()));
            return;
        }
        scrapTime += System.currentTimeMillis();
        webPage.setScrapTime(scrapTime);
        long processTime = -System.currentTimeMillis();
        findWordsAndSentences(webPage, doc);
        processTime += System.currentTimeMillis();
        webPage.setProcessTime(processTime);
        return;
    }

    private List<Word> getWords(List<String> words) {
        List<Word> wordList = new ArrayList<Word>();
        for (String word : words) {
            wordList.add(new Word(word));
        }
        return wordList;
    }
}
