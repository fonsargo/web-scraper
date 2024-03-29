package ru.spbsu.apmath.webscraper;

import ru.spbsu.apmath.webscraper.data.WebPage;
import ru.spbsu.apmath.webscraper.data.Word;

import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.spbsu.apmath.webscraper.PageUtils.findWordsAndSentences;
import static ru.spbsu.apmath.webscraper.PageUtils.getHtmlDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Афонин Сергей (hrundelb@yandex.ru)
 * Date: 07.08.2014
 * Time: 14:36
 */
public class Scraper {
    private List<WebPage> webPages;
    private List<String> originalWords;

    public Scraper(String url, List<String> words) {
        originalWords = words;
        List<Word> wordList = getWords(words);
        webPages = new ArrayList<WebPage>();
        webPages.add(new WebPage(url, wordList));
    }

    public Scraper(List<String> urls, List<String> words) {
        originalWords = words;
        webPages = new ArrayList<WebPage>();
        for (String url : urls) {
            List<Word> wordList = getWords(words);
            webPages.add(new WebPage(url, wordList));
        }
    }

    public void scrap() {
        for (WebPage webPage : webPages) {
            processPage(webPage);
        }
    }

    public void printResults(boolean printTimeSpent, boolean printNumberOfCharacters, boolean printNumberOfWords,
                             boolean printSentences) {
        List<Word> wordList = getWords(originalWords);
        WebPage totalPage = new WebPage("TOTAL", wordList);
        for (WebPage webPage : webPages) {
            webPage.print(printTimeSpent, printNumberOfCharacters, printNumberOfWords, printSentences);
            totalPage.add(webPage);
        }
        totalPage.print(printTimeSpent, printNumberOfCharacters, printNumberOfWords, printSentences);
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
    }

    private List<Word> getWords(List<String> words) {
        List<Word> wordList = new ArrayList<Word>();
        for (String word : words) {
            wordList.add(new Word(word));
        }
        return wordList;
    }
}
