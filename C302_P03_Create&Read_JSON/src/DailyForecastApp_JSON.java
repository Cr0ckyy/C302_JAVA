import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class DailyForecastApp_JSON {

    public static void main(String[] args) {

        String[] menu = {"Create Weather Forecast JSON file", "Display Weather Forecast"};

        var choice = Helper.getUserOption("WEATHER FORECAST", menu);

        while (choice != 0) {

            if (choice == 1) {
                createWeatherForecast_JSON();
            } else if (choice == 2) {
                readWeatherForecast_JSON();
            } else {
                System.out.println("Invalid choice. Please try again!");
            }

            System.out.println();
            choice = Helper.getUserOption("Weather Forecast", menu);

        }
    }

    // TODO: Case 1a - Create JSON file
    @SuppressWarnings("unchecked")
    private static void createWeatherForecast_JSON() {

        // TODO: Case 1a - Create JSON file

        // creating relevant JSONObjects
        JSONObject dailyForecastObj;
        dailyForecastObj = new JSONObject();
        var weatherData = new JSONObject();
        var tempArray = new JSONArray();
        var temp = new JSONObject();
        var maxTemp = new JSONObject();
        var minTemp = new JSONObject();
        var windSpeed = new JSONObject();

        // date object
        weatherData.put("date", "2018-04-01");

        // description object
        weatherData.put("description", "Sunny");

        // Temperature objects
        // maxTemp object
        maxTemp.put("unit", "°C");
        maxTemp.put("value", 32.0);
        temp.put("maxTemp", maxTemp);

        // minTemp object
        minTemp.put("unit", "°C");
        minTemp.put("value", 27.0);
        temp.put("minTemp", minTemp);

        // Adding Temperature objects into tempArray
        tempArray.add(temp);
        weatherData.put("temperatures", tempArray);

        // windSpeed object
        windSpeed.put("unit", "km/hr");
        windSpeed.put("value", 3);
        weatherData.put("windSpeed", windSpeed);

        dailyForecastObj.put("dailyForecast", weatherData);

        saveJSONFile(dailyForecastObj, "C302_P03_JSON_daily_forecast_results.json");

    }

    private static void saveJSONFile(JSONObject obj, String filename) {

        try {

            // TODO: Case 1b - Save JSON object to file
            var file = new FileWriter(filename);
            file.write(obj.toJSONString());
            file.flush();
            file.close();

            System.out.println(filename + " created successfully...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private static void readWeatherForecast_JSON() {

        // TODO: Case 2 - Read JSON file

        var parser = new JSONParser();

        try {

            var dailyForecastResultObj = parser.parse(new FileReader("C302_P03_JSON_daily_forecast_results.json"));
            var jsonObject = (JSONObject) dailyForecastResultObj;
            var dailyForecastObj = (JSONObject) jsonObject.get("dailyForecast");

            // get date object
            var date = (String) dailyForecastObj.get("date");
            System.out.println("Date: " + date);

            // get description object
            var description = (String) dailyForecastObj.get("description");
            System.out.println("Description: " + description);

            // get windSpeed object
            var windSpeed = (JSONObject) dailyForecastObj.get("windSpeed");
            var windSpeedUnit = (String) windSpeed.get("unit");
            var windSpeedValue = (Long) windSpeed.get("value");
            System.out.println("Wind Speed: " + windSpeedValue + windSpeedUnit);

            // get temperature objects
            var temperature = (JSONArray) dailyForecastObj.get("temperatures");
            // get maxTemp object
            var maxTemp = (JSONObject) ((JSONObject) temperature.get(0)).get("maxTemp");
            var maxTempUnit = (String) maxTemp.get("unit");
            var maxTempValue = (Double) maxTemp.get("value");
            System.out.println("Maximum Temperature: " + maxTempValue + maxTempUnit);
            // get minTemp object
            var minTemp = (JSONObject) ((JSONObject) temperature.get(0)).get("minTemp");
            var minTempUnit = (String) minTemp.get("unit");
            var minTempValue = (Double) minTemp.get("value");

            System.out.println("Minimum Temperature: " + minTempValue + maxTempUnit);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}