import java.util.ArrayList;
import java.util.Arrays;

public class Driver {

  public static void main(String[] args) {
    int maxLength = 12;
    Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
    for(Chromosome chromosome : population.chromosomes){
      System.out.println(chromosome);
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
}
