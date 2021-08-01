import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadEmployeesJSON {
	public static void main(String[] args) {

		var jSONParser = new JSONParser();

		try {

			var employeesJSONParser = jSONParser.parse(new FileReader("C302/C302_P03_JSON_employees.json'"));

			var employeejsonObject = (JSONObject) employeesJSONParser;

			var employeeJSONArray = (JSONArray) employeejsonObject.get("employees");

			for (var i = 0; i < employeeJSONArray.size(); i++) {
				var name = (String) employeeJSONArray.get(i);
				System.out.println("name: " + name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}