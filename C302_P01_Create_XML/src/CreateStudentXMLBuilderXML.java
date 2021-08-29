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

public class CreateStudentXMLBuilderXML {

    public static void main(String[] args) {
        try {

            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Create a Document
            Document document = builder.newDocument();

            ArrayList<String> JohnContact = new ArrayList<>();
            ArrayList<String> MaryContact = new ArrayList<>();
            ArrayList<Student> students = new ArrayList<>();

            students.add(new Student("John", JohnContact));
            students.add(new Student("Mary", MaryContact));

            JohnContact.add("98711240");
            JohnContact.add("61284612");

            Element rootElement = document.createElement("student");
            document.appendChild(rootElement);

            for (var stu : students) {

                // "student" element
                var studentsElement = document.createElement("students");
                var nameElement = document.createElement("name");
                var student = stu.getName();
                nameElement.setTextContent(student);
                rootElement.appendChild(studentsElement);
                studentsElement.appendChild(nameElement);

                Element contactsElement = document.createElement("contacts");
                for (var contactNo : stu.getContacts()) {

                    // "contact" element
                    Element contactElement = document.createElement("contact");
                    contactElement.setTextContent(contactNo);
                    contactsElement.appendChild(contactElement);
                }
                studentsElement.appendChild(contactsElement);
            }

            saveXML(document, "C302_P01_XML_student.xml");

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

        } catch (TransformerException e) {
            result = false;
        }

        return result;
    }
}
