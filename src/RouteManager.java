import java.util.ArrayList;

/*
* This class holds all the destination cities for our tour
* */

public class RouteManager {
    private static ArrayList<City> destinationCities = new ArrayList<City>();

    public static void addCity(City city) {
        destinationCities.add(city);
    }

    public static City getCity (int index) {
        return destinationCities.get(index);
    }

    public static int numberOfCities() {
        return destinationCities.size();
    }
}
