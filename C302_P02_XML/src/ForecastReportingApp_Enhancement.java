import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ForecastReportingApp_Enhancement {

    public static void main(String[] args) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();

            var xmlFile = new File("C302_P02_XML/three_days_forecast.xml");
            var document = builder.parse(xmlFile);

            var rootElement = document.getDocumentElement();

            var forecastNodeList = rootElement.getElementsByTagName("dailyForecast");
            for (int i = 0; i < forecastNodeList.getLength(); i++) {
                var forecastElement = (Element) forecastNodeList.item(i);

                var dateElement = (Element) forecastElement.getElementsByTagName("date").item(0);
                System.out.println("\ndate: " + dateElement.getTextContent());

                var descElement = (Element) forecastElement.getElementsByTagName("description").item(0);
                System.out.println("description: " + descElement.getTextContent());

                var windSpeedElement = (Element) forecastElement.getElementsByTagName("windSpeed").item(0);
                System.out.println("windSpeed: " + windSpeedElement.getTextContent());

                var tempElement = (Element) forecastElement.getElementsByTagName("temperatures").item(0);

                var maxTempElement = tempElement.getChildNodes().item(1);
                System.out.println("maxTemp: " + maxTempElement.getTextContent());

                var minTempElement = tempElement.getChildNodes().item(3);
                System.out.println("minTemp: " + minTempElement.getTextContent());

                System.out.println("===========================================================");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}