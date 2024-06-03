public class City {
    private String cityName;
    private String[] streets ;
    public City(String cityName, String[] streets) {
        this.cityName = cityName;
        this.streets = streets;

    }
    public String getCityName() {
        return cityName;
    }
    public String[] getStreets() {
        return streets;
    }
    public String toString() {
        return "City [cityName=" + cityName + ", streets=" + streets + "]";
    }

}
