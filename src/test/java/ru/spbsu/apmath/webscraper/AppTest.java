package ru.spbsu.apmath.webscraper;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static final String CNN = "http://www.cnn.com/";
    private static final String BBC = "http://www.bbc.com/";
    private static final String NYTIMES = "http://www.nytimes.com/";
    private static final String UNEXICTING_URL = "http://www.cnn123456.com/";
    private static final List<String> WORDS = Arrays.asList("says", "default", "China");

    @Test
    public void scraperTestWithOneUrl() throws MalformedURLException {
        URL url = new URL(CNN);
        Scraper scraper = new Scraper(url, WORDS);
        scraper.scrap();
        scraper.printResults();
    }

    @Test
    public void scraperTestWithUrls() throws MalformedURLException {
        List<URL> urls = new ArrayList<URL>();
        urls.add(new URL(CNN));
        urls.add(new URL(BBC));
        urls.add(new URL(NYTIMES));
        Scraper scraper = new Scraper(urls, WORDS);
        scraper.scrap();
        scraper.printResults();
    }

    @Test
    public void scraperTestWithException() throws MalformedURLException {
        List<URL> urls = new ArrayList<URL>();
        urls.add(new URL(CNN));
        urls.add(new URL(BBC));
        urls.add(new URL(UNEXICTING_URL));
        urls.add(new URL(NYTIMES));
        Scraper scraper = new Scraper(urls, WORDS);
        scraper.scrap();
        scraper.printResults();
    }
}
