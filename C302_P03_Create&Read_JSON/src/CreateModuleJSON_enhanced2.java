import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class CreateModuleJSON_enhanced2 {

    @SuppressWarnings({"unchecked", "unused"})
    public static void main(String[] args) {


        var obj1 = new JSONObject();
        var obj2 = new JSONObject();

        obj1.put("code", "C302");
        obj1.put("year", 3);
        obj1.put("title", "web Services");

        var classJSONArray = new JSONArray();
        classJSONArray.add("1-W64A");
        classJSONArray.add("1-W64B");
        classJSONArray.add("1-W64C");

        obj2.put("classes", classJSONArray);

        obj1.put("module", obj2);

        /*
         * <classes>
         * <class>1-W64A</class>
         * <class>2-W64B</class>
         *  <class>3-W64C</class>
         * </classes>
         */

        saveJSONFile(obj1, "C302_P03_JSON_module_enhanced2.json");
    }

    private static void saveJSONFile(JSONObject obj, String filename) {
        try {
            var file = new FileWriter(filename);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

            System.out.println(filename + " created successfully...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}