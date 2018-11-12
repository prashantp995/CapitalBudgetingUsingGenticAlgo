public class GeneticAlgorithm {

  public static final int POPULATION_SIZE = 50;
  public static final int target_chromosome_length = 12; //  total 3 year and we have total 4 projects , total 12 index
  public static final int NUMB_OF_ELITE_CHROMOSOMES = 1;
  public static final int TOURNAMENT_SELECTION_SIZE = 4;

  private Population crossoverPopulation(Population population) {
    Population crossoverPopulation = new Population(population.getChromosomes().length);
    for (int x = 0; x < NUMB_OF_ELITE_CHROMOSOMES; x++) {
      crossoverPopulation.getChromosomes()[x] = population.getChromosomes()[x];
    }
    for (int x = NUMB_OF_ELITE_CHROMOSOMES; x < population.getChromosomes().length; x++) {
      Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
      Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
      crossoverPopulation.getChromosomes()[x] = crossoverChromosome(chromosome1, chromosome2);
    }
    return crossoverPopulation;
  }

  public Population evolve(Population population) {
    return crossoverPopulation(population);
  }

  private Population selectTournamentPopulation(Population population) {
    Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
    for (int x = 0; x < TOURNAMENT_SELECTION_SIZE; x++) {
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
}
