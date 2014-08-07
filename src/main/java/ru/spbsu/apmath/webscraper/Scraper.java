package ru.spbsu.apmath.webscraper;

import ru.spbsu.apmath.webscraper.data.WebPage;
import ru.spbsu.apmath.webscraper.data.Word;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Афонин Сергей (hrundelb@yandex.ru)
 * Date: 07.08.2014
 * Time: 14:36
 */
public class Scraper {
    private List<WebPage> webPages;

    public Scraper(URL url, List<String> words) {
        List<Word> wordList = new ArrayList<Word>();
        for (String word: words) {
            wordList.add(new Word(word));
        }
        webPages = new ArrayList<WebPage>();
        webPages.add(new WebPage(url, wordList));
    }

    public void scrap() {
        for (WebPage webPage: webPages) {
            try {
                processPage(webPage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    private WebPage processPage(WebPage webPage) throws IOException, BadLocationException {
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
        doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
        Reader HTMLReader = new InputStreamReader(webPage.getUrl().openConnection().getInputStream());
        kit.read(HTMLReader, doc, 0);
        ElementIterator it = new ElementIterator(doc);
        Element elem;
        while ((elem = it.next()) != null) {
            if (elem.isLeaf()) {
                String text = doc.getText(elem.getStartOffset(), elem.getEndOffset() - elem.getStartOffset()).trim();
                for (Word word: webPage.getWords()) {
                    if (text.toLowerCase().indexOf(word.getWord().toLowerCase()) >= 0) {
                        System.out.println(word.getWord() + "---------------------->" + text);
                    }
                }
            }
        }
        return null;
    }
}
