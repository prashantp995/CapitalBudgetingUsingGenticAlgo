import java.util.ArrayList;
import java.util.Arrays;

public class Driver {

  public static void main(String[] args) {
    int maxGenerations = 120;
    Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
    System.out.println("-------------------------------------------------");
    System.out.println(
        "Generation # 0 " + " | Fittest chromosome fitness: " + population.chromosomes[0]
            .getFitness());
    printPopulation(population,
        "Target Chromosome: ");
    int generationNumber = 0;
    while (generationNumber < maxGenerations) {
      generationNumber++;
      System.out.println("\n-------------------------------------------------");
      population = geneticAlgorithm.evolve(population);
      population.sortChromosomesByFitness();
      System.out.println("Generation # " + generationNumber + " | Fittest chromosome fitness: " +
          population.chromosomes[0].getFitness());
      printPopulation(population,
          "Target Chromosome: ");
    }
  }

  public static void init() {

    int totalSamples = 180;
    int counter = 0;
    while (counter < totalSamples) {
      int[] testArray = new int[18];
      for (int x = 0; x < testArray.length; x++) {
        if (Math.random() >= 0.5) {
          testArray[x] = 1;
        } else {
          testArray[x] = 0;
        }
      }
      counter++;
      System.out.println("Testing with" + Arrays.toString(testArray));
    }
  }

  public static void printPopulation(Population population, String heading) {
    System.out.println(heading);
    System.out.println("-------------------------------------------------");
    for (int x = 0; x < population.chromosomes.length; x++) {
      System.out.println(
          "Chromosome  # " + x + "  : " + Arrays.toString(population.chromosomes[x].genes)
              +
              " | Fitness: " + population.chromosomes[x].getFitness());
    }
  }
}
