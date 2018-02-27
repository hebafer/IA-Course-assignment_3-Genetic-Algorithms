public class GeneticAlgorithm {
    private static double mutationRate = 0.01;
    private static int tournamentSize = 3;
    private static boolean elitism = true;

    //Evolves a population over one generation
    public static Population evolvePopulation(Population population){
        Population newPopulation = new Population(population.populationSize(), false);

        //Keep our best best individual in the position 0 if elitism = true
        int elitismOffset = 0;
        if (elitism){
            newPopulation.saveRoute(0, population.getFittest());
            elitismOffset = 1;
        }
        //Crossover population, iterate over the new population size
        //and create individuals from current population

        for(int i = elitismOffset; i < newPopulation.populationSize(); i++){
            //Select parents
            Route parent1 = tournamentSelection(population);
            Route parent2 = tournamentSelection(population);
            //Crossover parents
            Route child = crossover(parent1, parent2);
            //Add child to new population
            newPopulation.saveRoute(i, child);
        }

        //Mutate the new population with swap mutation
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++){
            mutate(newPopulation.getRoute(i));
        }

        return newPopulation;
    }

    //Apply crossover to a set of parents and creates offspring
    public static Route crossover(Route parent1, Route parent2){
        Route child = new Route();

        //Get start and end sub tour positions for parent1
        int startPosition = (int) (Math.random() * parent1.routeSize());
        int endPosition = (int) (Math.random() * parent1.routeSize());

        //Iterate and add the sub tour from parent1 to our child
        for(int i = 0; i < child.routeSize(); i++){
            //If our start position is less than the end position
            if (startPosition < endPosition && i > startPosition && i < endPosition){
                child.setCity(i, parent1.getCity(i));
            }//if our start position is larger than the end position
            else if (startPosition > endPosition){
                //Copy parent1 in child except between start and end position
                if(!(i < startPosition && i > endPosition)){
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        //Iterate through parent2 city tour
        for (int i = 0; i < parent2.routeSize(); i++){
            //If child does not have the city add it
            if(!child.containsCity(parent2.getCity(i))){
                // Iterate to find a empty position in the child tour
                for (int j = 0; j < child.routeSize(); j++){
                    //Empty position found, add city
                    if(child.getCity(j) == null){
                        child.setCity(j, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    //Mutate a route using swap mutation
    private static void mutate(Route route) {
        for(int routePosition1 = 0; routePosition1 < route.routeSize(); routePosition1++){
            //Apply mutation rate
            if(Math.random() < mutationRate){
                //Get a second random position in the route
                int routePosition2 = (int) (route.routeSize() * Math.random());

                //Get the cities from the two positions
                City city1 = route.getCity(routePosition1);
                City city2 = route.getCity(routePosition2);

                //Swap both cities
                route.setCity(routePosition1, city2);
                route.setCity(routePosition2, city1);
            }
        }
    }

    //Selects candidate tour for crossover
    private static Route tournamentSelection(Population population){
        //Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        //For each place in the tournament get a random candidate route and add it
        for (int i = 0; i < tournamentSize; i++){
            int randomId = (int)(Math.random() * population.populationSize());
            tournament.saveRoute(i, population.getRoute(randomId));
        }
        //Get the fittest tour
        Route fittest = tournament.getFittest();
        return fittest;
    }
}
