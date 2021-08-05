import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class CreateModuleJSON_enhanced {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        var jsonObject = new JSONObject();
        jsonObject.put("code", "C302");
        jsonObject.put("title", "web Services");
        jsonObject.put("year", 3);

        // put all the class into the JSONArray
        var classJSONArray = new JSONArray();
        classJSONArray.add("1-W64A");
        classJSONArray.add("1-W64B");
        classJSONArray.add("1-W64C");
        jsonObject.put("classes", classJSONArray);

        /*
         * <classes>
         * <class>1-W64A</class>
         * <class>2-W64B</class>
         * <class>3-W64C</class>
         * </classes>
         */


        System.out.println("\n" + jsonObject.toJSONString());
        saveJSONFile(jsonObject, "C302_P03_JSON_module_enhanced.json");
    }

    private static void saveJSONFile(JSONObject obj, String filename) {
        try {
            var file = new FileWriter(filename);
            file.write(obj.toJSONString()); // Writes a string.
            file.flush(); // Flushes the stream.
            file.close(); // Closes the stream, flushing it first.
            System.out.println(filename + " created successfully...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}