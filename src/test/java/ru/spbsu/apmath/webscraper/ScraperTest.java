package ru.spbsu.apmath.webscraper;

import org.junit.Test;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class ScraperTest {

    private static final String CNN = "http://www.cnn.com/";
    private static final String BBC = "http://www.bbc.com/";
    private static final String WASHINGTONPOST = "http://www.washingtonpost.com/";
    private static final String UNEXICTING_URL = "http://www.cnn123456.com/";
    private static final List<String> WORDS = Arrays.asList("has", "default", "Russia");

    @Test
    public void scraperTestWithOneUrl() throws MalformedURLException {
        Scraper scraper = new Scraper(CNN, WORDS);
        scraper.scrap();
        scraper.printResults(true, true, true, true);
    }

    @Test
    public void scraperTestWithUrls() throws MalformedURLException {
        Scraper scraper = new Scraper(Arrays.asList(CNN, BBC, WASHINGTONPOST), WORDS);
        scraper.scrap();
        scraper.printResults(true, true, true, true);
    }

    @Test
    public void scraperTestWithException() throws MalformedURLException {
        Scraper scraper = new Scraper(Arrays.asList(CNN, BBC, UNEXICTING_URL, WASHINGTONPOST), WORDS);
        scraper.scrap();
        scraper.printResults(true, true, true, true);
    }
}
