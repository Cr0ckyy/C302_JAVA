
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateModuleXMLBuilderXML {

	public static void main(String[] args) {
		try {

			// Create a DocumentBuilder
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Create a Document
			Document document = builder.newDocument();

			/*
			 Create the XML content with Element class. 
			 1. Create the Element object 
			 2. Set attributes and/or text where applicable 
			 3. Attach the element to its parent
			 */

			//"module" element
			Element rootElement = document.createElement("module");
			rootElement.setAttribute("code", "C302");
			document.appendChild(rootElement);

			// "title" element
			Element titleElement = document.createElement("title");
			titleElement.setTextContent("Web Service");
			rootElement.appendChild(titleElement);

			// "year" element
			Element yearElement = document.createElement("year");
			yearElement.setTextContent("3");
			rootElement.appendChild(yearElement);

			// Write the content into xml file
			saveXML(document, "C302_P01_XML_module.xml");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
	}

	// Finally, save the XML document by using the helper method:
	// Eg. saveXML(document, "module_c302.xml");
	private static boolean saveXML(Document document, String filename) {
		TransformerFactory factory = TransformerFactory.newInstance();
		boolean result = true;

		try {
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			Source src = new DOMSource(document);
			Result dest = new StreamResult(new File(filename));
			transformer.transform(src, dest);

			System.out.println(filename + " created successfully...");

		} catch (TransformerConfigurationException e) {
			result = false;
		} catch (TransformerException e) {
			result = false;
		}

		return result;
	}
}
