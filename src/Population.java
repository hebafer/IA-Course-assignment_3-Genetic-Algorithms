public class Population {

    //Population of routes
    Route[] routes;

    //Construct a population
    public Population(int populationSize, boolean initialize){
        routes = new Route[populationSize];
        if (initialize){
            for(int i = 0; i < populationSize(); i++){
                Route newRoute = new Route();
                newRoute.generateIndividual();
                saveRoute(i, newRoute);
            }
        }
    }

    //Save a route
    public void saveRoute(int index, Route route){
        this.routes[index] = route;
    }

    //Get a tour from population
    public Route getRoute(int index){
        return this.routes[index];
    }

    //Get the best tour in the population
    public Route getFittest(){
        Route fittest = routes[0];
        for (int i = 1; i < populationSize(); i++){
            if (fittest.getFitness() <= getRoute(i).getFitness()){
                fittest = getRoute(i);
            }
        }
        return fittest;
    }
    //Get population size
    public int populationSize(){
        return routes.length;
    }
}
