import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadBookXML {

    public static void main(String[] args) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();

            var xmlFile = new File("C302_P02_XML/book.xml");
            var document = builder.parse(xmlFile);

            var rootElement = document.getDocumentElement();
            System.out.println("\nRoot Element: " + rootElement.getTagName());

            var titleElement = rootElement.getElementsByTagName("title");
            var langElement = (Element) titleElement.item(0);
            System.out.println("Title: " + titleElement.item(0).getTextContent());
            System.out.println("Language: " + langElement.getAttribute("lang"));

            var authorElement = rootElement.getElementsByTagName("author");
            System.out.println("Author: " + authorElement.item(0).getTextContent());

            var yearElement = rootElement.getElementsByTagName("year");
            System.out.println("Year: " + yearElement.item(0).getTextContent());

            var priceElement = rootElement.getElementsByTagName("price");
            var unitElement = (Element) priceElement.item(0);
            System.out.println(
                    "Price: " + unitElement.getAttribute("unit") + " " + priceElement.item(0).getTextContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}