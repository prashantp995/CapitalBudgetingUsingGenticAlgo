public class GeneticAlgorithm {

  public static final int POPULATION_SIZE = 50;
  public static final int target_chromosome_length = 12; //  total 3 year and we have total 4 projects , total 12 index
  public static final int TOURNAMENT_SELECTION = 4;
  public static final double MUTATION_RATE = 0.01;

  private Population crossoverPopulation(Population population, int generationNumber) {
    Population crossoverPopulation = new Population(population.getChromosomes().length);

    for (int x = 0; x < population.getChromosomes().length; x++) {
      Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
      Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
      System.out.println(
          "Generation # " + generationNumber + "] Selected for cross over " + chromosome1 + "and "
              + chromosome2);
      crossoverPopulation.getChromosomes()[x] = crossoverChromosome(chromosome1, chromosome2);
      System.out
          .println("Result of cross over is  " + crossoverPopulation.getChromosomes()[x] + "\n");
    }
    return crossoverPopulation;
  }

  public Population evolve(Population population, int generationNumber) {
    return mutatePopulation(crossoverPopulation(population, generationNumber));
  }

  private Population selectTournamentPopulation(Population population) {
    Population tournamentPopulation = new Population(TOURNAMENT_SELECTION);
    for (int x = 0; x < TOURNAMENT_SELECTION; x++) {
      tournamentPopulation.getChromosomes()[x] =
          population.getChromosomes()[(int) (Math.random() * population.getChromosomes().length)];
    }
    tournamentPopulation.sortChromosomesByFitness();
    return tournamentPopulation;
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
      if (Math.random() < MUTATION_RATE) {
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
