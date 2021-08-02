import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ForecastReportingApp_pojo {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			var xmlFile = new File("C302_P02_XML/daily_forecast.xml");
			var document = builder.parse(xmlFile);

			var forecastReport = new ForecastReport();

			var rootElement = document.getDocumentElement();

			// "id" element
			var dateElement = (Element) rootElement.getElementsByTagName("date").item(0);
			forecastReport.setDate(dateElement.getTextContent());

			// "description" element
			var descElement = (Element) rootElement.getElementsByTagName("description").item(0);
			forecastReport.setDesc(descElement.getTextContent());

			// "windSpeed" element
			var windElement = (Element) rootElement.getElementsByTagName("windSpeed").item(0);
			forecastReport.setWindSpeed(Double.parseDouble(windElement.getTextContent()));
			forecastReport.setWindUnit(windElement.getAttribute("unit"));

			// "temperatures" element
			var tempElement = (Element) rootElement.getElementsByTagName("temperatures").item(0);
			var maxTempElement = (Element) tempElement.getElementsByTagName("maxTemp").item(0);
			forecastReport.setMaxTemp(Double.parseDouble(maxTempElement.getTextContent()));
			forecastReport.setMaxTempUnit(maxTempElement.getAttribute("unit"));

			// "minTemp" element
			var minTempElement = (Element) tempElement.getElementsByTagName("minTemp").item(0);
			forecastReport.setMinTemp(Double.parseDouble(minTempElement.getTextContent()));
			forecastReport.setMinTempUnit(minTempElement.getAttribute("unit"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}