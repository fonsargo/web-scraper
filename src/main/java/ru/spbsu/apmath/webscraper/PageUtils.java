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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Афонин Сергей (hrundelb@yandex.ru)
 * Date: 07.08.2014
 * Time: 19:54
 */
public class PageUtils {

    public static void findWordsAndSentences(WebPage webPage, HTMLDocument doc) {
        ElementIterator it = new ElementIterator(doc);
        Element elem;
        while ((elem = it.next()) != null) {
            if (elem.isLeaf()) {
                String text = null;
                try {
                    text = doc.getText(elem.getStartOffset(), elem.getEndOffset() - elem.getStartOffset()).trim();
                } catch (BadLocationException e) {
                    webPage.setErrorMessage(e.getMessage());
                    return;
                }
                webPage.addToNumberOfCharacters(text.length());
                for (Word word: webPage.getWords()) {
                    findWords(text, word);     //We need to find words and sentences separately, because one sentence can include two or more words
                    findSentences(text, word);
                }
            }
        }
    }

    public static void findWords(String text, Word word) {
        Pattern wordPattern = Pattern.compile(String.format("(\\W+|^)%s(\\W+|$)", word.getWord()));
        Matcher wordMatcher = wordPattern.matcher(text);
        while (wordMatcher.find()) {
            word.increaseCount();
        }
    }

    public static void findSentences(String text, Word word) {
        Pattern sentencePattern = Pattern.compile(String.format("[^\\.!?]*([^\\w\\.!?]+|^)%s([\\.!?]|\\W+[^\\.!?]*|$)",
                word.getWord()));
        Matcher sentenceMatcher = sentencePattern.matcher(text);
        while (sentenceMatcher.find()) {
            word.addSentence(sentenceMatcher.group(0));
        }
    }

    public static HTMLDocument getHtmlDocument(URL url) throws IOException, BadLocationException {
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
        doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
        Reader HTMLReader = new InputStreamReader(url.openConnection().getInputStream());
        kit.read(HTMLReader, doc, 0);
        return doc;
    }
}
