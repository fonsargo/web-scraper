package ru.spbsu.apmath.webscraper;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void generalTest() throws MalformedURLException {
        String s = "http://www.cnn.com/";
        URL url = new URL(s);
        Scraper scraper = new Scraper(url, Arrays.asList("says", "default", "China"));
        scraper.scrap();
        scraper.printResults();
    }
}
