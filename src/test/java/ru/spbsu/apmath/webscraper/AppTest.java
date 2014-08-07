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
        URL url = new URL("http://www.cnn.com/");
        Scraper scraper = new Scraper(url, Arrays.asList("Greece", "default"));
        scraper.scrap();
    }
}
