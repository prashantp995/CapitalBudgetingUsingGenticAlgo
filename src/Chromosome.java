import java.util.Arrays;

public class Chromosome {

  int genes[];
  int fitness;

  public Chromosome(int length) {
    genes = new int[length];
  }

  public int getFitness() {
    fitness = calculateFitness(genes);
    return fitness;
  }

  public Chromosome initializeChromosome() {
    for (int x = 0; x < genes.length; x++) {
      if (Math.random() >= 0.5) {
        genes[x] = 1;
      } else {
        genes[x] = 0;
      }
    }
    return this;
  }


  public static int calculateFitness(int[] values) {
    int[] year1 = Arrays.copyOfRange(values, 0, 4);
    int[] year2 = Arrays.copyOfRange(values, 4, 8);
    int[] year3 = Arrays.copyOfRange(values, 8, 12);
    System.out.println("y1" + Arrays.toString(year1));
    System.out.println("y2" + Arrays.toString(year2));
    System.out.println("y3" + Arrays.toString(year3));
    return 0;
  }

  @Override
  public String toString() {
    return Arrays.toString(genes) + "\n";
  }
}
