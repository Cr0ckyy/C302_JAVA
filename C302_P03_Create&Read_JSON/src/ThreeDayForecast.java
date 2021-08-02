public class ThreeDayForecast {

    private String date;
    private String description;
    private String maxTempUnit;
    private double maxTempValue;
    private String minTempUnit;
    private double minTempValue;
    private String windSpeedUnit;
    private int windSpeedValue;

    public ThreeDayForecast(String date, String description, String maxTempUnit, double maxTempValue,
                            String minTempUnit, double minTempValue, String windSpeedUnit, int windSpeedValue) {
        this.date = date;
        this.description = description;
        this.maxTempUnit = maxTempUnit;
        this.maxTempValue = maxTempValue;
        this.minTempUnit = minTempUnit;
        this.minTempValue = minTempValue;
        this.windSpeedUnit = windSpeedUnit;
        this.windSpeedValue = windSpeedValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxTempUnit() {
        return maxTempUnit;
    }

    public void setMaxTempUnit(String maxTempUnit) {
        this.maxTempUnit = maxTempUnit;
    }

    public double getMaxTempValue() {
        return maxTempValue;
    }

    public void setMaxTempValue(double maxTempValue) {
        this.maxTempValue = maxTempValue;
    }

    public String getMinTempUnit() {
        return minTempUnit;
    }

    public void setMinTempUnit(String minTempUnit) {
        this.minTempUnit = minTempUnit;
    }

    public double getMinTempValue() {
        return minTempValue;
    }

    public void setMinTempValue(double minTempValue) {
        this.minTempValue = minTempValue;
    }

    public String getWindSpeedUnit() {
        return windSpeedUnit;
    }

    public void setWindSpeedUnit(String windSpeedUnit) {
        this.windSpeedUnit = windSpeedUnit;
    }

    public int getWindSpeedValue() {
        return windSpeedValue;
    }

    public void setWindSpeedValue(int windSpeedValue) {
        this.windSpeedValue = windSpeedValue;
    }

}