import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadStudentsXML {

	public static void main(String[] args) {
		try {
			var factory = DocumentBuilderFactory.newInstance();
			var builder = factory.newDocumentBuilder();

			var xmlFile = new File("C302_P02_XML/students.xml");
			var document = builder.parse(xmlFile);

			// Get document root element "modules"
			// it returns the root element of the document
			var rootElement = document.getDocumentElement();

			// Task 1: Get all "module" elements from rootElement
			// it accesses all elements with the specified tagname
			var studentNodeList = rootElement.getElementsByTagName("student");

			for (int i = 0; i < studentNodeList.getLength(); i++) {

				// getNodeType() specifies the type of node
				if (studentNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

					// list.item(index) returns the node at the specified index,
					// or null if the index is out of range.
					var studentElement = (Element) studentNodeList.item(i);
					System.out.println("Student Name: " + studentElement.getAttribute("name"));

					var contactsNodeList = studentElement.getElementsByTagName("contacts");
					System.out.println("# of contacts: " + contactsNodeList.getLength());

					for (int j = 0; j < contactsNodeList.getLength(); j++) {
						if (contactsNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
							var contactElement = (Element) contactsNodeList.item(j);
							// getTextContent() returns the text for the specified node
							System.out.println("Contact: " + contactElement.getTextContent());
						}
					}

					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}