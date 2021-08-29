import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReadJSONObject {

    public static void main(String[] args) {

        try {
            // TODO: 1.To enable the url, open the Xampp Apache project
            URL url = new URL("http://localhost/C302_P05_Sakila/person.json");

            // TODO: 2.Begin a connection with a designated URL
            URLConnection conn = url.openConnection();

            // TODO: 3.Sets the doOutput field of this URLConnection to the given value
            conn.setDoOutput(true);

            // TODO: 4. To retrieve data from the URL-enabled connection output by parsing JSON and reading buffer character-input stream
            var parser = new JSONParser();
            Object obj = parser.parse(
                    new BufferedReader(
                            new InputStreamReader(conn.getInputStream()))
            );
            // TODO: 5. Get the respective variables from JSONObject
            var jsonObject = (JSONObject) obj;
            var personObj = (JSONObject) jsonObject.get("person");
            var name = (String) personObj.get("name");
            var age = (Long) personObj.get("age");

            System.out.println(name + " is " + age + " years old.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}