import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class CreateDailyForecastAppPoJoXML {

    public static void main(String[] args) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Create a Document
            Document document = builder.newDocument();

            ArrayList<DailyForecast> data = new ArrayList<>();
            data.add(new DailyForecast("2018-04-01", "Sunny", 32, 27, 3));

            // "DailyForecastsData" element
            Element rootElement = document.createElement("DailyForecastsData");
            document.appendChild(rootElement);

            for (DailyForecast d : data) {

                // "dailyForecast" & "date" elements
                var dailyForecastElement = document.createElement("dailyForecast");
                var dateElement = document.createElement("date");
                dateElement.setTextContent(d.getDate());
                dailyForecastElement.appendChild(dateElement);

                // "description element
                var descriptionElement = document.createElement("description");
                descriptionElement.setTextContent(d.getDescription());
                dailyForecastElement.appendChild(descriptionElement);

                // "temperatures" & "maxTemp" elements
                var temperaturesElement = document.createElement("temperatures");
                var maxTempElement = document.createElement("maxTemp");
                maxTempElement.setAttribute("unit", "C");
                maxTempElement.setTextContent(d.getMaxTemp());
                temperaturesElement.appendChild(maxTempElement);

                // minTemp element
                Element minTempElement = document.createElement("minTemp");
                minTempElement.setAttribute("unit", "C");
                minTempElement.setTextContent(d.getMinTemp());
                temperaturesElement.appendChild(minTempElement);
                dailyForecastElement.appendChild(temperaturesElement);

                // minTemp windSpeed
                Element windSpeedElement = document.createElement("windSpeed");
                windSpeedElement.setAttribute("unit", "kph");
                windSpeedElement.setTextContent(d.getWindSpeed());
                dailyForecastElement.appendChild(windSpeedElement);

                rootElement.appendChild(dailyForecastElement);
            }
            // Write the content into xml file
            saveXML(document, "C302_P01_XML_dailyForecast_pojo.xml");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
    }

    private static boolean saveXML(Document document, String filename) {
        TransformerFactory factory = TransformerFactory.newInstance();
        boolean result = true;

        try {
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "4");

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
