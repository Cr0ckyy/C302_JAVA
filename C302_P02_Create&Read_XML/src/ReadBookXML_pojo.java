import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadBookXML_pojo {

	public static void main(String[] args) {
		try {
			var factory = DocumentBuilderFactory.newInstance();
			var builder = factory.newDocumentBuilder();

			var xmlFile = new File("C302_P02_XML/book.xml");
			var document = builder.parse(xmlFile);

			var book = new Book();

			var rootElement = document.getDocumentElement();
			System.out.println("Root Element: " + rootElement.getTagName());

			var titleElement = rootElement.getElementsByTagName("title");
			var langElement = (Element) titleElement.item(0);
			book.setTitle(titleElement.item(0).getTextContent());
			book.setLang(langElement.getAttribute("lang"));

			var authorElement = rootElement.getElementsByTagName("author");
			book.setAuthor(authorElement.item(0).getTextContent());

			var yearElement = rootElement.getElementsByTagName("year");
			book.setYear(yearElement.item(0).getTextContent());

			var priceElement = rootElement.getElementsByTagName("price");
			var unitElement = (Element) priceElement.item(0);
			book.setUnit(unitElement.getAttribute("unit"));
			book.setPrice(priceElement.item(0).getTextContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}