import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadBookJSON {
    public static void main(String[] args) {
        System.out.println();
        var parser = new JSONParser();

        try {

            var bookJSONParser = parser.parse(new FileReader("C302_P03_JSON_book.json"));
            var bookJSONObject = (JSONObject) bookJSONParser;

            var title = (String) bookJSONObject.get("title");
            System.out.println("title: " + title);

            var author = (String) bookJSONObject.get("author");
            System.out.println("author: " + author);

            var year = (Long) bookJSONObject.get("year");
            System.out.println("year: " + year);

            var price = (Double) bookJSONObject.get("price");
            System.out.println("price: " + price);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}