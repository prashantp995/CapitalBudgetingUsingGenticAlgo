public class GeneticAlgorithm {

  public static final int POPULATION_SIZE = 6;
  public static final int MAX_GENERATION = 12;
  public static final int target_chromosome_length = 4;
  public static final double CROSSOVER_RATE = 0.75;
  public static final double MUTATION_RATE = 0.01;


  private Population crossoverPopulation(Population population, int generationNumber) {
    Population crossoverPopulation = new Population(population.getChromosomes().length);

    for (int x = 0; x < population.getChromosomes().length; x++) {
      Chromosome chromosome1 = getChromosomeFromPopulation(population);
      Chromosome chromosome2 = getChromosomeFromPopulation(population);
      if (Math.random() <= CROSSOVER_RATE) {
        System.out.println(
            "Generation # " + generationNumber + "] Selected for cross over " + chromosome1 + "and "
                + chromosome2);
        crossoverPopulation.getChromosomes()[x] = crossoverChromosome(chromosome1, chromosome2);
        System.out
            .println("Result of cross over is  " + crossoverPopulation.getChromosomes()[x] + "\n");
      } else {
        if (Math.random() <= 0.50) {
          crossoverPopulation.getChromosomes()[x] = chromosome1;
        } else {
          crossoverPopulation.getChromosomes()[x] = chromosome2;
        }

      }


    }
    return crossoverPopulation;
  }

  public Population evolve(Population population, int generationNumber) {
    return mutatePopulation(crossoverPopulation(population, generationNumber));
  }

  /*
    Select any random 4 chromosome and sort it according to fitness and send it for cross over
   */
  private Chromosome getChromosomeFromPopulation(Population population) {
    Population tournamentPopulation = new Population(4);
    for (int x = 0; x < 4; x++) {
      tournamentPopulation.getChromosomes()[x] =
          population.getChromosomes()[(int) (Math.random() * population.getChromosomes().length)];
    }
    tournamentPopulation.sortChromosomesByFitness();
    return tournamentPopulation.getChromosomes()[0];
  }

  private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
    Chromosome crossoverChromosome = new Chromosome(target_chromosome_length);
    for (int x = 0; x < chromosome1.getGenes().length; x++) {
      if (Math.random() < 0.5) {
        crossoverChromosome.getGenes()[x] = chromosome1.getGenes()[x];
      } else {
        crossoverChromosome.getGenes()[x] = chromosome2.getGenes()[x];
      }
    }
    return crossoverChromosome;
  }

  private Population mutatePopulation(Population population) {
    Population mutatePopulation = new Population(population.getChromosomes().length);

    for (int x = 0; x < population.getChromosomes().length; x++) {
      mutatePopulation.getChromosomes()[x] = mutateChromosome(population.getChromosomes()[x]);
    }
    return mutatePopulation;
  }

  private Chromosome mutateChromosome(Chromosome chromosome) {
    Chromosome mutateChromosome = new Chromosome(target_chromosome_length);
    for (int x = 0; x < chromosome.getGenes().length; x++) {
      //mutation rate will decide whether to mutate or not
      if (Math.random() < MUTATION_RATE) {
        //once chromosome selected for mutation then assign 0 or 1 randomly to the genes
        if (Math.random() < 0.5) {
          mutateChromosome.getGenes()[x] = 1;
        } else {
          mutateChromosome.getGenes()[x] = 0;
        }
      } else {
        mutateChromosome.getGenes()[x] = chromosome.getGenes()[x];
      }
    }
    return mutateChromosome;
  }
}
