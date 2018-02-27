/*
 * This class stores a candidate tour (or route of cities)
 * */

import java.util.ArrayList;
import java.util.Collections;

public class Route {

    private ArrayList route = new ArrayList<City>();
    private double fitness = 0;
    private int distance = 0;

    public Route(){
        for (int i = 0; i < RouteManager.numberOfCities(); i++){
            route.add(null);
        }
    }

    //Creates a random individual
    public void generateIndividual(){
        //Loop through all our destination cities and add them to our route
        for (int cityIndex = 0; cityIndex < RouteManager.numberOfCities(); cityIndex++){
            setCity(cityIndex, RouteManager.getCity(cityIndex));
        }
        // Randomly reorder the route
        Collections.shuffle(this.route);
    }

    //Gets a city from the route
    public City getCity(int routePosition){
        return (City) route.get(routePosition);
    }

    //Sets a city in a certain position within a route
    public void setCity(int routePosition, City city){
        route.set(routePosition, city);
        //It the route is altered we need to reset the fitness and distance
        this.fitness = 0;
        this.distance = 0;
    }

    //Gets the routes fitness
    public double getFitness() {
        if (fitness == 0){
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }

    //Gets the total distance of a route
    public int getDistance(){
        int routeDistance = 0;
        if (distance == 0){
            //Iterate over all route's cities
            for (int cityIndex = 0; cityIndex < this.route.size(); cityIndex++){
                //Get city we are travelling rom
                City actualCity = getCity(cityIndex);
                //Destination city
                City destinationCity;
                //Check we are not on our route's last city, if we are
                //set our route final destination city to our starting city
                if(cityIndex + 1 < this.route.size()){
                    destinationCity = getCity(cityIndex + 1);
                }
                else{
                    destinationCity = getCity(0);
                    //routeDistance will be 0
                }
                //Get the distance between the two cities
                routeDistance += actualCity.getDistance(destinationCity);
            }
        }
        return routeDistance;
    }

    //Get number of cities on our route
    public int routeSize(){
        return route.size();
    }
    //Check if the route contains a city
    public boolean containsCity(City city){
        return route.contains(city);
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < this.route.size(); i++) {
            geneString += getCity(i)+"|";
        }
        return geneString;
    }
}
