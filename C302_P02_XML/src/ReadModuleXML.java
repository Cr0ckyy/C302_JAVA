import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadModuleXML {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {

			var factory = DocumentBuilderFactory.newInstance();
			var builder = factory.newDocumentBuilder();

			var xmlFile = new File("module_c302.xml");
			var document = builder.parse(xmlFile);

			// Get document root element "module"
			var rootElement = document.getDocumentElement();
			// getTagName() returns the tag name for the specified element
			System.out.println("Root Element: " + rootElement.getTagName());

			// Get attribute for "module" element
			var moduleCode = rootElement.getAttribute("code");
			System.out.println("Module Code: " + moduleCode);

			// Get "title" element
			var titleNodeList = document.getElementsByTagName("title");
			var titleNode = titleNodeList.item(0);
			System.out.println("Title: " + titleNode.getTextContent());

			// Get "year" element
			var yearNodeList = document.getElementsByTagName("year");
			var yearNode = yearNodeList.item(0);
			System.out.println("Year: " + yearNode.getTextContent());

			// Cast a node to become an element
			var titleElement = (Element) titleNode;
			// getAttribute(attribute name) returns the attribute with the requested name,
			// for the specified element
			var language = titleElement.getAttribute("lang");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}