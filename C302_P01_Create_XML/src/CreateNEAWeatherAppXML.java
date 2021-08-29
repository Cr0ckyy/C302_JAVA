import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CreateNEAWeatherAppXML {
    public CreateNEAWeatherAppXML() {
    }

    public static void main(String[] args) {
        try {

            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Create a Document
            Document document = builder.newDocument();

            /*
             * Create the XML content with Element class. 1. Create the Element object 2.
             * Set attributes and/or text where applicable 3. Attach the element to its
             * parent
             */

            // "feed" element
            var rootElement = document.createElement("feed");
            rootElement.setTextContent("http://www.w3.org/2005/Atom");
            document.appendChild(rootElement);

            // "title" element
            var titleElement = document.createElement("title");
            titleElement.setTextContent("1 Day Forecast");
            rootElement.appendChild(titleElement);

            // 1st "link" element
            var linkElement = document.createElement("link");
            linkElement.setAttribute("href", "http://www.nea.gov.sg/weather_3day.aspx/");
            linkElement.setAttribute("rel", "self");
            rootElement.appendChild(linkElement);

            // 2nd "link" element
            linkElement = document.createElement("link");
            linkElement.setAttribute("href", "http://www.nea.gov.sg/");
            rootElement.appendChild(linkElement);

            // "updated" element
            var updatedElement = document.createElement("updated");
            updatedElement.setTextContent("2018-03-31T10:13:00Z");
            rootElement.appendChild(updatedElement);

            // "author" & "name" elements
            var authorElement = document.createElement("author");
            var nameElement = document.createElement("name");
            nameElement.setTextContent("Meteorological Service Singapore");
            authorElement.appendChild(nameElement);
            rootElement.appendChild(authorElement);

            // "id" element
            var idElement = document.createElement("id");
            idElement.setTextContent("urn:uuid:20180331-d399-11d9-b93C-051405003af6");
            rootElement.appendChild(idElement);

            // "content" , "div" , "img" , "forecast" elements
            var contentElement = document.createElement("content");
            contentElement.setAttribute("type", "xhtml"); // e.g. <content type="xhtml">
            // "div" element
            var divElement = document.createElement("div");
            divElement.setAttribute("xmlns", "http://www.w3.org/1999/xhtml"); // e.g.
            // xmlns="http://www.w3.org/1999/xhtml
            // "img" element
            var imgElement = document.createElement("img");
            imgElement.setAttribute("src",
                    "http://www.weather.gov.sg/wp-content/themes/wiptheme/assets/img/icon-fair-warm.png");
            divElement.appendChild(imgElement); // e.g. src="
            // http://www.weather.gov.sg/wp-content/themes/wiptheme/assets/img/icon-fair-warm.png"
            // "forecast" element
            var forecaseElement = document.createElement("forecast");
            forecaseElement.setTextContent("Fair and warm.");
            divElement.appendChild(forecaseElement);
            contentElement.appendChild(divElement);
            rootElement.appendChild(contentElement);

            // Write the content into xml file
            saveXML(document, "C302_P01_XML_NEA_WeatherApp.xml");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    private static boolean saveXML(Document document, String filename) {
        TransformerFactory factory = TransformerFactory.newInstance();
        boolean result = true;

        try {
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            Source src = new DOMSource(document);
            Result dest = new StreamResult(new File(filename));
            transformer.transform(src, dest);
            System.out.println(filename + " created successfully...");
        } catch (TransformerConfigurationException var7) {
            result = false;
        } catch (TransformerException var8) {
            result = false;
        }

        return result;
    }
}
