package robot.ANN;

import java.util.Random;

import robot.GeneticAlgorithm.IChromosome;

public class RandomLinearNeuralNetwork extends AbstractNeuralNetwork implements IChromosome {
    private static final double PROBABILITY_NEWNODE = 0.01;
    Random random = new Random();

    /**
     * add a random node to the network. Mind you that this node initially does
     * nothing because it has no connections to other nodes.
     */
    private void addRandomNode() {
        addNeuronToNetwork(new LinearEvaluationFunction());
    }

    /**
     * Add a random connection to the network. This method just keeps randomly
     * selecting predecessors/successors until he successfully selected a pair
     * that he can couple without introducing a loop in the network.
     */
    private void addRandomConnection() {
        boolean success = false;
        double weight = random.nextDouble();
        while (!success) {
            Neuron successor = getRandomSuccessor();
            AbstractNeuron predecessor = getRandomPredecessor();
            success = addPredecessorToNeuron(predecessor, successor, weight);
        }
    }

    private Neuron getRandomSuccessor() {
        int index = random.nextInt(output.size() + neurons.size());
        return index < output.size() ? output.get(index) : neurons.get(index - output.size());
    }

    private AbstractNeuron getRandomPredecessor() {
        int index = random.nextInt(input.size() + neurons.size());
        return index < input.size() ? input.get(index) : neurons.get(index - input.size());
    }

    @Override
    public void mutate() {
        if (random.nextDouble() < PROBABILITY_NEWNODE) {
            addRandomNode();
        }
        addRandomConnection();
    }

    @Override
    public IChromosome procreate(IChromosome partner) {
        // TODO god knows how I'm gonna implement this
        return null;
    }

    @Override
    public double fitness() {
        // TODO run a game and return the score
        return 0;
    }
}
