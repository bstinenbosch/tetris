package robot.ANN;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import robot.ANN.Neuron.AbstractNeuron;
import robot.ANN.Neuron.InputNeuron;
import robot.ANN.Neuron.Neuron;
import robot.ANN.Neuron.OutputNeuron;
import robot.ANN.functions.LinearEvaluationFunction;
import robot.GeneticAlgorithm.IChromosome;

public class RandomLinearNeuralNetwork extends AbstractNeuralNetwork implements IChromosome {
    private static final double PROBABILITY_NEWNODE = 0.01;
    private static final double PROBABILITY_INHERIT = 0.5;
    Random random = new Random();

    public RandomLinearNeuralNetwork() {
        super();
    }

    /**
     * add a random node to the network. Mind you that this node initially does
     * nothing because it has no connections to other nodes.
     */
    private void addRandomNode() {
        addNeuronToNetwork(new LinearEvaluationFunction());
        // TODO add this node to an input.
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
        // perhaps:
        // TODO remove node with some probability
        // TODO remove random connection
    }

    @Override
    public IChromosome procreate(IChromosome partner) {
        RandomLinearNeuralNetwork child = deepCloneIO();
        inherit(this, child);
        inherit((RandomLinearNeuralNetwork) partner, child);
        return child;
    }

    private void inherit(RandomLinearNeuralNetwork parent, RandomLinearNeuralNetwork child) {
        HashMap<AbstractNeuron, Neuron> oldNewTable = new HashMap<AbstractNeuron, Neuron>(
            neurons.size());
        NeuralPathNode node;
        Neuron neuron;
        AbstractNeuron previousNeuron;
        double previousWeight;
        for (LinkedList<NeuralPathNode> path : parent.getIterable()) {
            if (random.nextDouble() < PROBABILITY_INHERIT) {
                node = path.pop();
                previousNeuron = node.getNeuron();
                previousWeight = node.getWeight();
                // TODO implement equals() for input and output that equates
                // their IInput and IOutput interface implementations, not their
                // memory address.
                // TODO treat the last element in the list different? (is
                // output)
                // traverse path: check all neurons against oldnewtable. if not
                // present add, else use corresponding new. add the weights and
                // the predecessors according to the path.
                while (!path.isEmpty()) {
                    node = path.pop();
                    if (!oldNewTable.containsKey(node.getNeuron())) {
                        neuron = new Neuron(new LinearEvaluationFunction());
                        oldNewTable.put(node.getNeuron(), neuron);
                    } else {
                        neuron = oldNewTable.get(node.getNeuron());
                    }
                    neuron.add(previousNeuron, previousWeight);
                    previousNeuron = neuron;
                    previousWeight = node.getWeight();
                }
            }
        }
    }

    @Override
    public double fitness() {
        // TODO run 1 game and return the score
        return 0;
    }

    private RandomLinearNeuralNetwork deepCloneIO() {
        RandomLinearNeuralNetwork child = new RandomLinearNeuralNetwork();
        for (InputNeuron iNeuron : input) {
            child.addInputToNetwork(iNeuron.getInput());
        }
        for (OutputNeuron oNeuron : output) {
            child.addOutputToNetwork(oNeuron.getOutput(), new LinearEvaluationFunction());
        }
        return null;
    }

    public static void main(String[] args) {
    }
}
