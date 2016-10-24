package robot.ANN;

import java.util.ArrayList;

import robot.ANN.Neuron.AbstractNeuron;
import robot.ANN.Neuron.IInput;
import robot.ANN.Neuron.IOutput;
import robot.ANN.Neuron.InputNeuron;
import robot.ANN.Neuron.Neuron;
import robot.ANN.Neuron.OutputNeuron;
import robot.ANN.functions.EvaluationFunction;
import robot.GeneticAlgorithm.IChromosome;

public abstract class AbstractNeuralNetwork implements IChromosome {
    protected ArrayList<InputNeuron> input = new ArrayList<InputNeuron>();
    protected ArrayList<OutputNeuron> output = new ArrayList<OutputNeuron>();
    protected ArrayList<Neuron> neurons = new ArrayList<Neuron>();
    protected EvaluationFunction function;
    protected double fitness = -1;

    public AbstractNeuralNetwork(EvaluationFunction function, IInput[] inputs, IOutput[] outputs) {
        super();
        this.function = function;
        for (IInput input : inputs) {
            addInputToNetwork(input);
        }
        for (IOutput output : outputs) {
            addOutputToNetwork(output, function);
        }
    }

    public AbstractNeuralNetwork(EvaluationFunction function) {
        super();
        this.function = function;
    }

    public PathIterable getIterable() {
        return new PathIterable(output.iterator());
    }

    /**
     * update evaluates the current state of the neural network and fires the
     * output events accordingly.
     */
    public void evaluate() {
        reset();
        for (OutputNeuron node : output) {
            node.getCharge();
        }
    }

    /**
     * reset discharges all nodes, preparing the network for the next
     * evaluation.
     */
    private void reset() {
        for (OutputNeuron node : output) {
            node.uncharge();
        }
    }

    /**
     * Add an output neuron to the network. Typically, after this step it is
     * necessary to call addPredecessorToNeuron to hook the output to input in
     * some way.
     * 
     * @param output
     */
    public final OutputNeuron addOutputToNetwork(IOutput output, EvaluationFunction function) {
        OutputNeuron oNeuron = new OutputNeuron(function, output);
        this.output.add(oNeuron);
        return oNeuron;
    }

    /**
     * Add an input neuron to the network. Typically, a network is built
     * propagating forwards: Hence this would be the first step in building a
     * network.
     * 
     * @param input
     */
    public final InputNeuron addInputToNetwork(IInput input) {
        InputNeuron iNeuron = new InputNeuron(input);
        this.input.add(iNeuron);
        return iNeuron;
    }

    /**
     * Add an intermediate neuron to the network. Typically requires hooking
     * predecessors afterwards.
     * 
     * @param function
     * @return the neuron that was added.
     */
    protected final Neuron addNeuronToNetwork(EvaluationFunction function) {
        Neuron neuron = new Neuron(function);
        neurons.add(neuron);
        return neuron;
    }

    protected final boolean addPredecessorToNeuron(AbstractNeuron predecessor, Neuron neuron,
        double weight) {
        if (predecessor.hasAsPredecessor(neuron)) {
            return false;
        }
        neuron.add(predecessor, weight);
        return true;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
