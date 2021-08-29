import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReadJSONArray {
    public static void main(String[] args) {

        try {
            // TODO: 1.To enable the url, open the Xampp Apache project
            URL url = new URL("http://localhost/C302_P05_Sakila/modules.json");

            // TODO: 2.Begin a connection with a designated URL
            URLConnection conn = url.openConnection();

            // TODO: 3.Sets the doOutput field of this URLConnection to the given value
            conn.setDoOutput(true);

            // TODO: 4. To retrieve data from the URL-enabled connection output by parsing JSON and reading buffer character-input stream
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new BufferedReader(
                    new InputStreamReader(conn.getInputStream())));

            // TODO: 5. Get the respective JSONObject from JSONArray through iteration
            JSONArray moduleArray = (JSONArray) obj;
            for (int i = 0; i < moduleArray.size(); i++) {

                // TODO: 6. Get the respective variables from JSONObject
                JSONObject moduleObj = (JSONObject) moduleArray.get(i);
                String moduleCode = (String) moduleObj.get("module_code");
                String moduleName = (String) moduleObj.get("module_name");

                System.out.println((i + 1) + ": " + moduleName + " has a module code of  " + moduleCode);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}