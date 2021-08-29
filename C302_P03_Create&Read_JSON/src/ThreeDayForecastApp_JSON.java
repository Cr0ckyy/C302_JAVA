import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import static java.lang.System.out;

public class ThreeDayForecastApp_JSON {

    @SuppressWarnings({"unchecked", "unused"})
    public static void main(String[] args) {

        // create
        ArrayList<ThreeDayForecast> weathers = new ArrayList<>();

        weathers.add(new ThreeDayForecast("2018-04-01", "Sunny", "C", 32.0, "C", 27.0, "km/hr", 3));
        weathers.add(new ThreeDayForecast("2018-04-02", "Rainy", "C", 28.0, "C", 23.0, "km/hr", 7));
        weathers.add(new ThreeDayForecast("2018-04-03", "Cloudy", "C", 30.0, "C", 25.0, "km/hr", 5));

        var weatherJSONArray = new JSONArray();

        for (var weather : weathers) {

            var dailyForecastObj = new JSONObject();
            var weatherData = new JSONObject();
            var tempArray = new JSONArray();
            var temp = new JSONObject();
            var maxTemp = new JSONObject();
            var minTemp = new JSONObject();
            var windSpeed = new JSONObject();

            // get date object
            weatherData.put("date", weather.getDate());

            // get description object
            weatherData.put("description", weather.getDescription());

            // get Temperature objects
            // get maxTemp object
            maxTemp.put("unit", weather.getMaxTempUnit());
            maxTemp.put("value", weather.getMaxTempValue());
            temp.put("maxTemp", maxTemp);

            // get minTemp object
            minTemp.put("unit", weather.getMinTempUnit());
            minTemp.put("value", weather.getMinTempValue());
            temp.put("minTemp", minTemp);

            // Adding Temperature objects into jsonArray
            tempArray.add(temp);
            weatherData.put("temperatures", tempArray);

            // get windSpeed object
            windSpeed.put("unit", weather.getWindSpeedUnit());
            windSpeed.put("value", weather.getWindSpeedValue());
            weatherData.put("windSpeed", windSpeed);

            dailyForecastObj.put("dailyForecast", weatherData);
            weatherJSONArray.add(dailyForecastObj);
        }
        saveJSONFile(weatherJSONArray, "C302_P03_JSON_ThreeDayForecast.json");

        // read

        var parser = new JSONParser();

        try {

            var threeDayForecastJSONObject = parser.parse(new FileReader("C302_P03_JSON_ThreeDayForecast.json"));
            var weatherJSONObjects = (JSONArray) threeDayForecastJSONObject;

            for (var i : weatherJSONObjects) {

                JSONObject weatherJSONObject = (JSONObject) i;
                JSONObject dailyForecastJSONObject = (JSONObject) weatherJSONObject.get("dailyForecast");

                String date = (String) dailyForecastJSONObject.get("date");
                out.println("Date: " + date);

                var description = (String) dailyForecastJSONObject.get("description");
                out.println("Description: " + description);

                var windSpeed = (JSONObject) dailyForecastJSONObject.get("windSpeed");
                var windSpeedUnit = (String) windSpeed.get("unit");
                var windSpeedValue = (Long) windSpeed.get("value");
                out.println("Wind Speed: " + windSpeedValue + windSpeedUnit);

                var temperature = (JSONArray) dailyForecastJSONObject.get("temperatures");
                var maxTemp = (JSONObject) ((JSONObject) temperature.get(0)).get("maxTemp");
                var maxTempUnit = (String) maxTemp.get("unit");
                var maxTempValue = (Double) maxTemp.get("value");
                out.println("Maximum Temperature: " + maxTempValue + maxTempUnit);

                var minTemp = (JSONObject) ((JSONObject) temperature.get(0)).get("minTemp");
                var minTempUnit = (String) minTemp.get("unit");
                var minTempValue = (Double) minTemp.get("value");
                out.println("Minimum Temperature: " + minTempValue + maxTempUnit + "\n");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveJSONFile(JSONArray obj, String filename) {

        try {
            var file = new FileWriter(filename);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

            out.println(filename + " created successfully...\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}