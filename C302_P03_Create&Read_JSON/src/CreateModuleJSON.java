import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class CreateModuleJSON {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        var jSONObject = new JSONObject();

        jSONObject.put("module_code", "C302");
        jSONObject.put("title", "web Services");
        jSONObject.put("year", 3);

        System.out.println("\n" + jSONObject.toJSONString());

        saveJSONFile(jSONObject, "C302_P03_JSON_module.json");
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