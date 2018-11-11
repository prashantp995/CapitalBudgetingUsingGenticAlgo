import java.util.Arrays;

public class Population {

  Chromosome[] chromosomes;

  public Population(int populationSize) {
    chromosomes = new Chromosome[populationSize];
  }

  public Population initializePopulation() {
    for (int x = 0; x < chromosomes.length; x++) {
      chromosomes[x] = new Chromosome(GeneticAlgorithm.target_chromosome_length)
          .initializeChromosome();
    }
    sortChromosomesByFitness();
    return this;
  }

  public void sortChromosomesByFitness() {
    Arrays.sort(chromosomes, (chromosome1, chromosome2) -> {
      int flag = 0;
      if (chromosome1.getFitness() > chromosome2.getFitness()) {
        flag = -1;
      } else if (chromosome1.getFitness() < chromosome2.getFitness()) {
        flag = 1;
      }
      return flag;
    });
  }


  @Override
  public String toString() {
    return Arrays.toString(chromosomes);
  }
}
