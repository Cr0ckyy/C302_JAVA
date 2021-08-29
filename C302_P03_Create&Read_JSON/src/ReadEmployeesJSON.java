import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadEmployeesJSON {

    public static void main(String[] args) {

        var jSONParser = new JSONParser();

        try {

            var employeesJSONParser = jSONParser.parse(new FileReader("C302_P03_JSON_employees.json"));

            var employeeJsonObject = (JSONObject) employeesJSONParser;

            var employeeJSONArray = (JSONArray) employeeJsonObject.get("employees");
            System.out.println();

            for (var name : employeeJSONArray) {
                System.out.println("name: " + name);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


}