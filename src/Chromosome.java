import java.text.DecimalFormat;
import java.util.Arrays;

public class Chromosome {

  int genes[];
  double fitness;

  public Chromosome(int length) {
    genes = new int[length];
  }

  public int[] getGenes() {
    return genes;
  }

  public double getFitness() {
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


  public static double calculateFitness(int[] values) {
    int[] year1 = Arrays.copyOfRange(values, 0, 4);
    int[] year2 = Arrays.copyOfRange(values, 4, 8);
    int[] year3 = Arrays.copyOfRange(values, 8, 12);

    double Y1Constraint = 0.5 * year1[0] + 1.0 * year1[1] + 1.5 * year1[2] + 0.1 * year1[3];
    double Y2Constraint = 0.3 * year2[0] + 0.8 * year2[1] + 1.5 * year2[2] + 0.4 * year2[3];
    double Y3Constraint = 0.2 * year3[0] + 0.2 * year3[1] + 0.3 * year3[2] + 0.1 * year3[3];

    double budgetY1 = 3.1;
    double budgetY2 = 2.5;
    double budgetY3 = 0.4;

    double fitnessY1 = 0.0;
    double fitnessY2 = 0.0;
    double fitnessY3 = 0.0;

    if (Y1Constraint <= budgetY1) {
      fitnessY1 = 0.2 * year1[0] + 0.3 * year1[1] + 0.5 * year1[2] + 0.1 * year1[3];
    }
    if (Y2Constraint <= budgetY2) {
      fitnessY2 = 0.2 * year2[0] + 0.3 * year2[1] + 0.5 * year2[2] + 0.1 * year2[3];
    }
    if (Y3Constraint <= budgetY3) {
      fitnessY3 = 0.2 * year3[0] + 0.3 * year3[1] + 0.5 * year3[2] + 0.1 * year3[3];
    }

    double maxFitness = fitnessY1 + fitnessY2 + fitnessY3;
    DecimalFormat dec = new DecimalFormat("#0.00");
    return Double.parseDouble(dec.format(maxFitness));

  }

  @Override
  public String toString() {
    return Arrays.toString(genes) ;
  }
}
