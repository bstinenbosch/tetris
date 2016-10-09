package robot.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {

    private static final double MUTATION_PROBABILITY = 0.01;
    private IChromosome[] population;
    private double[] fitness;
    private ArrayList<Double> generationFitness;
    Random random = new Random();

    // TODO populate the IChromosome with neural networks
    public GeneticAlgorithm(int populationStart) {
        population = new IChromosome[populationStart * 2];
        fitness = new double[populationStart * 2];
    }

    public void run(int cycles) {
        while (generationFitness.size() < cycles) {
            cycle();
        }
    }

    private void cycle() {
        double averageFitness = averageFitness();
        generationFitness.add(averageFitness);
        ArrayList<IChromosome> selectedMates = cullUnfit(averageFitness);
        procreate(selectedMates);
        mutatePopulation();
    }

    /**
     * let the selectedMates repopulate civilization.
     * 
     * @param selectedMates
     */
    private void procreate(ArrayList<IChromosome> selectedMates) {
        for (int i = 0; i < fitness.length; i++) {
            IChromosome mother, father;
            if (fitness[i] == 0) {
                mother = selectedMates.get(random.nextInt(selectedMates.size()));
                father = selectedMates.get(random.nextInt(selectedMates.size()));
                // yes, possibly mother==father. Keep an open mind!
                population[i] = mother.procreate(father);
            }
        }
    }

    /**
     * Be that eugenicist and mark everyone for culling who performs below
     * average.
     * 
     * @param averageFitness
     *            the benchmark
     * @return return a list of who hasn't been culled
     */
    private ArrayList<IChromosome> cullUnfit(double averageFitness) {
        ArrayList<IChromosome> selectedMates = new ArrayList<IChromosome>(
            (int) (fitness.length / 2));
        for (int i = 0; i < fitness.length; i++) {
            if (fitness[i] < averageFitness) {
                fitness[i] = 0;
            } else {
                selectedMates.add(population[i]);
            }
        }
        return selectedMates;
    }

    /**
     * Mutate a small portion of the member of the population.
     */
    private void mutatePopulation() {
        for (int i = 0; i < population.length; i++) {
            if (random.nextDouble() < MUTATION_PROBABILITY) {
                fitness[i] = 0;
                population[i].mutate();
            }
        }
    }

    /**
     * update the fitness of each individual and return the population average
     * fitness.
     * 
     * @return
     */
    private double averageFitness() {
        double averageFitness = 0;
        for (int i = 0; i < fitness.length; i++) {
            if (fitness[i] == 0) {
                fitness[i] = population[i].fitness();
            }
            averageFitness += fitness[i];
        }
        averageFitness /= fitness.length;
        return averageFitness;
    }
}
