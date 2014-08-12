package ru.spbsu.apmath.webscraper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class App
{
    public static void main( String[] args ) {
        try {
            List<String> words = getWords(args[1]);
            Scraper scraper = getScraper(args[0], words);
            boolean v = false;
            boolean c = false;
            boolean w = false;
            boolean e = false;
            for (int i = 2; i < args.length; i++) {
                if (args[i].length() != 2) {
                    throw new IllegalArgumentException(String.format("Invalid data command: %s", args[i]));
                }
                switch (args[i].charAt(1)) {
                    case 'v':
                        v = true;
                        break;
                    case 'c':
                        c = true;
                        break;
                    case 'w':
                        w = true;
                        break;
                    case 'e':
                        e = true;
                        break;
                    default:
                        break;
                }
            }
            if (!(v || c || w || e)) {
                throw new IllegalArgumentException("Data commands was not found");
            }
            scraper.scrap();
            scraper.printResults(v, c, w, e);
        } catch (IllegalArgumentException iae) {
            System.out.println(String.format("%s: %s", iae.getClass().getName(), iae.getMessage()));
        } catch (IndexOutOfBoundsException ioe) {
            System.out.println("One or more arguments was not found.");
            printHelpString();
        } catch (Exception e) {
            System.out.println(String.format("%s: %s", e.getClass().getName(), e.getMessage()));
            printHelpString();
        }
    }

    private static void printHelpString() {
        System.out.println("Use: webscraper [URL or path to file with URLs] [word (or list of words with ',' delimiter)] [data command(s)]");
        System.out.println("Supports the following data processing commands:");
        System.out.println("\t-v\toutput verbosity flag,  if on then the output should contains information about time spend on data scraping and data processing");
        System.out.println("\t-c\tcount number of characters of each web page");
        System.out.println("\t-w\tcount number of provided word(s) occurrence on webpage(s)");
        System.out.println("\t-e\textract sentences’ which contain given words");
    }

    private static Scraper getScraper(String arg, List<String> words) {
        Scraper scraper;
        try {
            String url = new URL(arg).toString();
            scraper = new Scraper(url, words);
        } catch (MalformedURLException e) {
            List<String> urls = new ArrayList<String>();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(arg));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    urls.add(line);
                }
            } catch (FileNotFoundException fe) {
                throw new IllegalArgumentException("First argument should be web resources URL or " +
                        "path to plain text file containing a list of URLs");
            } catch (IOException ioe) {
                throw new IllegalArgumentException("First argument should be web resources URL or " +
                        "path to plain text file containing a list of URLs");
            }
            scraper = new Scraper(urls, words);
        }
        return scraper;
    }

    private static List<String> getWords(String arg) {
        List<String> words = new ArrayList<String>();
        StringTokenizer stringTokenizer = new StringTokenizer(arg, ",");
        while(stringTokenizer.hasMoreTokens()) {
            words.add(stringTokenizer.nextToken());
        }
        if (words.size() == 0) {
            throw new IllegalArgumentException("Count of words should be more than 0");
        }
        return words;
    }
}
