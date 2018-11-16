import java.util.Arrays;

public class Driver {

  public static void main(String[] args) {

    int fitnessTrack = 0;
    double fittestChromosomeFitness = 0.0;
    printInitialDetails();
    Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
    System.out.println("-------------------------------------------------");
    System.out.println("Initial Population");
    System.out.println(
        "Total 3 year and we have total 4 projects , total 12 index , for that reason  12 genes per chromosome");
    System.out.println(
        "Generation # 0 " + " | Fittest chromosome fitness: " + population.chromosomes[0]
            .getFitness());
    printPopulation(population,
        "Population of this generation is (Sorted based on Fitness )");
    int generationNumber = 0;
    fittestChromosomeFitness = population.chromosomes[0]
        .getFitness();
    /*
      stopping criteria :
      1 )if generation exceed maximum number of allowed generation stop it
      2 ) if there is no fitness change over 3 continuous generation , break the loop
     */
    while (generationNumber < GeneticAlgorithm.MAX_GENERATION) {
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
      /**
       * if there is no change in fitness over 3 generation break it
       */
      if (population.chromosomes[0].getFitness() == fittestChromosomeFitness) {
        fitnessTrack++;
        if (fitnessTrack > 3) {
          System.out.println(
              "Stopping regardless of generation remaining ,because there is no change found in previous 3 generations");
          break;
        }
      }

    }
  }


  private static void printInitialDetails() {
    System.out.println("Mutation Probability is " + GeneticAlgorithm.MUTATION_RATE);
    System.out.println("Size of the population is " + GeneticAlgorithm.POPULATION_SIZE);
  }


  public static void printPopulation(Population population, String tital) {
    System.out.println(tital);
    System.out.println("-------------------------------------------------");
    for (int x = 0; x < population.chromosomes.length; x++) {
      System.out.println(
          "Chromosome  # " + x + "  : " + Arrays.toString(population.chromosomes[x].genes)
              +
              " | Fitness: " + population.chromosomes[x].getFitness());
    }
  }
}
