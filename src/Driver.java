import java.util.Arrays;

public class Driver {

  public static void main(String[] args) {
    int maxGenerations = 12;
    printInitialDetails();
    Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
    System.out.println("-------------------------------------------------");
    System.out.println("Initial Population");
    System.out.println(
        "total 3 year and we have total 4 projects , total 12 index , for that reason  12 genes per chromosome");
    System.out.println(
        "Generation # 0 " + " | Fittest chromosome fitness: " + population.chromosomes[0]
            .getFitness());
    printPopulation(population,
        "Population of this generation is (Sorted based on Fitness )");
    int generationNumber = 0;
    while (generationNumber < maxGenerations) {
      generationNumber++;
      System.out.println("\n-------------------------------------------------");
      System.out.println("Process start for the Generation # " + generationNumber);
      population = geneticAlgorithm.evolve(population, generationNumber);
      population.sortChromosomesByFitness();
      System.out
          .println("Process end for the Generation # " + generationNumber + " below is the result");
      System.out.println(
          "Generation # " + generationNumber + " | Maximum return found in this generation is " +
              population.chromosomes[0].getFitness());
      printPopulation(population,
          "Population of this generation is (Sorted based on Fitness ): ");
    }
  }

  private static void printInitialDetails() {
    System.out.println("Mutation Probability is " + GeneticAlgorithm.MUTATION_RATE);
    System.out.println("Size of the population is " + GeneticAlgorithm.POPULATION_SIZE);
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
