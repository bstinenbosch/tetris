package robot.GeneticAlgorithm;

import robot.ANN.RandomLinearNeuralNetwork;

public class ChromosomeFactory {

    public static IChromosome getChromosome(Chromosome type) {
        switch (type) {
            case RandomLinearNN:
                return new RandomLinearNeuralNetwork();
            default:
                throw new UnsupportedOperationException(
                    "You tried to create a chromosome that is not known by the chromosome factory.");
        }
    }
}
