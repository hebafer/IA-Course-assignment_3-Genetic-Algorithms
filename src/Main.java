import java.io.File;
import java.util.Scanner;

public class Main {

    public static void initializeProblem(){
        try {

            Scanner input = new Scanner("Assignment 3 berlin52.tsp");
            File file = new File(input.nextLine());
            input = new Scanner(file);

            // Skip first 6 lines
            for (int i = 0; i < 6; i++) {
                input.nextLine();
            }

            String[] parts;
            while (input.hasNextLine() && !input.hasNext("EOF")) {
                parts = input.nextLine().split(" ");
                RouteManager.addCity(new City(Double.parseDouble(parts[1]), Double.parseDouble((parts[2]))));
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //Read cities form file and initialize problem
        initializeProblem();
        // Initialize population
        Population population = new Population(500, true);
        System.out.println("Initial distance: " + population.getFittest().getDistance());

        // Evolve population for 1000 generations
        population = GeneticAlgorithm.evolvePopulation(population);
        for (int i = 0; i < 1000; i++) {
            population = GeneticAlgorithm.evolvePopulation(population);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + population.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(population.getFittest());

    }
}
