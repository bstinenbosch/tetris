package robot.GeneticAlgorithm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import robot.ANN.AbstractNeuralNetwork;
import robot.ANN.Neuron.IInput;
import robot.ANN.Neuron.IOutput;
import robot.ANN.functions.AbstractEvaluationFunction;

public class GeneticAlgorithm {

    private static final double MUTATION_PROBABILITY = 0.01;
    private LinkedList<IChromosome> population;
    private LinkedList<IChromosome> newborns;
    private ArrayList<Double> generationFitness;
    private final double generationSize;
    private double totalFitness;
    Random random = new Random();

    public GeneticAlgorithm(int populationStart,
        Class<? extends AbstractEvaluationFunction> function,
        Class<? extends AbstractNeuralNetwork> ANN, IInput[] inputs, IOutput[] outputs) {
        population = new LinkedList<IChromosome>();
        generationSize = populationStart * 2;
        while (newborns.size() < generationSize) {
            try {
                newborns.push(ANN.getConstructor(function, IInput.class, IOutput.class)
                    .newInstance(function, inputs, outputs));
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * return the next newborn and if necessary cycle the generation.
     * 
     * @return the next newborn.
     */
    public IChromosome getNextRobot() {
        if (newborns.isEmpty()) {
            cycle();
        }
        population.push(newborns.pop());
        return population.getFirst();

    }

    /**
     * perform the three evolutionary steps: kill, procreate, mutate.
     */
    private void cycle() {
        recountTotalFitness();
        generationFitness.add(totalFitness / generationSize);
        cullUnfit();
        procreate();
        mutatePopulation();
    }

    /**
     * let the selectedMates repopulate civilization.
     * 
     * @param selectedMates
     * 
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void procreate() {
        while (population.size() + newborns.size() < generationSize) {
            IChromosome mother, father;
            mother = population.get(random.nextInt(population.size()));
            father = population.get(random.nextInt(population.size()));
            // yes, possibly mother==father. Keep an open mind!
            newborns.push(mother.procreate(father));
        }
    }

    /**
     * Be that eugenicist and mark everyone for culling who performs below
     * average.
     * 
     */
    private void cullUnfit() {
        population
            .removeIf((chromosome) -> chromosome.getFitness() < totalFitness / generationSize);
    }

    /**
     * Mutate a small portion of the member of the population.
     * 
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void mutatePopulation() {
        for (int i = 0; i < generationSize; i++) {
            if (random.nextDouble() < MUTATION_PROBABILITY) {
                population.get(i).mutate();
                newborns.push(population.remove(i));
            }
        }
    }

    /**
     * update the fitness of each individual and return the population average
     * fitness.
     * 
     */
    private void recountTotalFitness() {
        totalFitness = 0;
        for (IChromosome chromosome : population) {
            totalFitness += chromosome.getFitness();
        }
    }
}
