import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CreateDailyForecastAppXML {

    public static void main(String[] args) {
        try {
            // Create a DocumentBuilder
            var factory = DocumentBuilderFactory.newInstance();
			var builder = factory.newDocumentBuilder();

            // Create a Document
			var document = builder.newDocument();

            /*
             * Create the XML content with Element class. 1. Create the Element object 2.
             * Set attributes and/or text where applicable 3. Attach the element to its
             * parent
             */

            // "dailyForecast" element
            var rootElement = document.createElement("dailyForecast");
            document.appendChild(rootElement);

            // "date" element
            var dateElement = document.createElement("date");
            dateElement.setTextContent("2018-04-01");
            rootElement.appendChild(dateElement);

            // "description" element
            var descriptionElement = document.createElement("description");
            descriptionElement.setTextContent("Sunny");
            rootElement.appendChild(descriptionElement);

            // "temperatures" element
            var temperaturesElement = document.createElement("temperatures");

            // "maxTemp" element
            var maxTempElement = document.createElement("maxTemp");
            maxTempElement.setAttribute("unit", "C");
            maxTempElement.setTextContent("32");
            temperaturesElement.appendChild(maxTempElement);

            // "minTemp" element
            var minTempElement = document.createElement("minTemp");
            minTempElement.setAttribute("unit", "C");
            minTempElement.setTextContent("27");
            temperaturesElement.appendChild(minTempElement);
            rootElement.appendChild(temperaturesElement);

            // "windSpeed" element
            var windSpeedElement = document.createElement("windSpeed");
            windSpeedElement.setAttribute("unit", "kph");
            windSpeedElement.setTextContent("3");
            rootElement.appendChild(windSpeedElement);

            // Write the content into xml file
            saveXML(document, "C302_P01_XML_dailyForecast.xml");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
    }

    private static void saveXML(Document document, String filename) {
        var factory = TransformerFactory.newInstance();
        boolean result = true;

        try {
			var transformer = factory.newTransformer();
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

	}
}
