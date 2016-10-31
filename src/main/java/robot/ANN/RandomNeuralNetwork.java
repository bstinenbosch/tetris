package robot.ANN;

import java.util.HashMap;
import java.util.Random;

import robot.ANN.Neuron.AbstractNeuron;
import robot.ANN.Neuron.IInput;
import robot.ANN.Neuron.IOutput;
import robot.ANN.Neuron.InputNeuron;
import robot.ANN.Neuron.Neuron;
import robot.ANN.Neuron.OutputNeuron;
import robot.ANN.functions.EvaluationFunction;
import robot.GeneticAlgorithm.IChromosome;

public class RandomNeuralNetwork extends AbstractNeuralNetwork {

    private static final double PROBABILITY_NEWNODE = 0.05;
    private static final double PROBABILITY_INHERIT = 0.5;
    Random random = new Random();

    public RandomNeuralNetwork(EvaluationFunction function, IInput[] inputs, IOutput[] outputs) {
        super(function, inputs, outputs);
    }

    public RandomNeuralNetwork(EvaluationFunction function) {
        super(function);
    }

    /**
     * add a random node to the network.
     */
    private void addRandomNode() {
        // Neuron successor =
        addNeuronToNetwork(function);
        // addPredecessorToNeuron(getRandomPredecessor(), successor,
        // random.nextDouble());
    }

    /**
     * Add a random connection to the network. This method just keeps randomly
     * selecting predecessors/successors until he successfully selected a pair
     * that he can couple without introducing a loop in the network.
     */
    private void addRandomConnection() {
        boolean success = false;
        double weight = random.nextGaussian();
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
        RandomNeuralNetwork child = deepCloneIO();
        inherit(this, child);
        inherit((RandomNeuralNetwork) partner, child);
        return child;
    }

    private void inherit(RandomNeuralNetwork parent, RandomNeuralNetwork child) {
        HashMap<AbstractNeuron, Neuron> oldNewTable = new HashMap<>(neurons.size());
        PathNode node;
        Neuron neuron;
        AbstractNeuron previousNeuron;
        double previousWeight;
        for (Path path : parent) {
            if (random.nextDouble() < PROBABILITY_INHERIT) {
                node = path.pop();
                previousNeuron = node.getNeuron();
                previousWeight = node.getWeight();
                // TODO implement equals() for input and output that equates
                // their IInput and IOutput interface implementations, not their
                // memory address. SOLVED create iinput and ioutput only once.
                // TODO treat the last element in the list different? (is
                // output)
                // traverse path: check all neurons against oldnewtable. if not
                // present add, else use corresponding new. add the weights and
                // the predecessors according to the path.
                while (!path.isEmpty()) {
                    node = path.pop();
                    if (!oldNewTable.containsKey(node.getNeuron())) {
                        neuron = new Neuron(function);
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

    private RandomNeuralNetwork deepCloneIO() {
        RandomNeuralNetwork child = new RandomNeuralNetwork(function);
        for (InputNeuron iNeuron : input) {
            child.addInputToNetwork(iNeuron.getInput());
        }
        for (OutputNeuron oNeuron : output) {
            child.addOutputToNetwork(oNeuron.getOutput());
        }
        return null;
    }

}
