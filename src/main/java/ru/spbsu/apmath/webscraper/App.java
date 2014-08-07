package ru.spbsu.apmath.webscraper;

import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class App
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        try {
            URL url = new URL("http://www.cnn.com/");
            HTMLEditorKit kit = new HTMLEditorKit();
            HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
            doc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
            Reader HTMLReader = new InputStreamReader(url.openConnection().getInputStream());
            kit.read(HTMLReader, doc, 0);

            ElementIterator it = new ElementIterator(doc);
            Element elem;

            while ((elem = it.next()) != null) {
                if (elem.isLeaf()) {
                    String text = doc.getText(elem.getStartOffset(), elem.getEndOffset() - elem.getStartOffset()).trim();
                    if (text.toLowerCase().indexOf("latest") >= 0) {
                        System.out.println("---------------------->" + text);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
