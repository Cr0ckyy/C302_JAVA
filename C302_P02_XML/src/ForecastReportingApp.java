import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ForecastReportingApp {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			
			//TODO: Task 1
			var xmlFile = new File("C302_P02_XML/daily_forecast.xml");
			var document = builder.parse(xmlFile);

			var rootElement = document.getDocumentElement();
			System.out.println(rootElement.getTagName());
			System.out.println("\n==============================================================================================================");

			var dateElement = (Element) rootElement.getElementsByTagName("date").item(0);
			System.out.println("Date: " + dateElement.getTextContent());

			var descElement = (Element) rootElement.getElementsByTagName("description").item(0);
			System.out.println("Description: " + descElement.getTextContent());

			var windElement = (Element) rootElement.getElementsByTagName("windSpeed").item(0);
			System.out.println("Wind Speed: " + windElement.getTextContent() + windElement.getAttribute("unit"));

			var tempElement = (Element) rootElement.getElementsByTagName("temperatures").item(0);
			var maxTempElement = (Element) tempElement.getElementsByTagName("maxTemp").item(0);
			System.out.println(
					"Maximum Temperature: " + maxTempElement.getTextContent() + maxTempElement.getAttribute("unit"));
			var minTempElement = (Element) tempElement.getElementsByTagName("minTemp").item(0);
			System.out.println(
					"Minimum Temperature: " + minTempElement.getTextContent() + minTempElement.getAttribute("unit"));

			//TODO: Task 2
			var outDocument = builder.newDocument();

			var outRootElement = outDocument.createElement("dailyForecastReport");
			outDocument.appendChild(outRootElement);

			var outDateElement = outDocument.createElement("date");
			outDateElement.setTextContent(dateElement.getTextContent());
			outRootElement.appendChild(outDateElement);

			var outSummaryElement = outDocument.createElement("summary");
			outSummaryElement
					.setTextContent("The weather is Sunny with temperature range of " + minTempElement.getTextContent()
							+ " to " + maxTempElement.getTextContent() + "degree celsius. The wind speed is "
							+ windElement.getTextContent() + windElement.getAttribute("unit"));
			outRootElement.appendChild(outSummaryElement);

			saveXML(outDocument, "C302_P02_XML_daily_forecast_reporting.xml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void saveXML(Document document, String filename) {
		var factory = TransformerFactory.newInstance();
		var result = true;

		try {
			var transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			var src = new DOMSource(document);
			var dest = new StreamResult(new File(filename));
			transformer.transform(src, dest);

			System.out.println("\n"+ filename + " created successfully...");

		} catch (TransformerException e) {
			result = false;
		}

	}
}