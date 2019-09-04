# AI Course - Genetic Algorithms

Solution for the Assignment 3. 

Solve the Travel Salesman Problem (TSP) using a genetic algorithm.

## Description of the Operations

-Initialize: to initialize the population of our genetic algorithm we create as many individuals as the population size. Each individual, will be a list with a random route. 
-Selection: to select the parents to crossover we have implemented tournament selection, we choose k random individuals from the population and select the best one according to the fitness function (the minimum distance in a route).  
-Crossover: we generate the offspring as follows: first we copy a fixed number of cities from a random interval of the first parent in the child. Later we go through the cities of the second parent and we add those that are not includedin the child following a first come, first served strategy. 
-Mutation:to mutate an individual we just swap two random cities of the route.

## Parameters of the Algorithm

-Population size: our genetic algorithm has a population size of 500 individuals.
-Crossover rate: in our implementation the crossover always occurswhen we create a new offspring for the next generation, thus the crossover rate is equal to 1.
-Mutation rate: the mutation rate measuresthe probability that parts of the chromosome of an individual changed. In our implementation the rate is equal to 0.015
-Number of generations: Our evolutionaryalgorithm is executed 1000 generations.
