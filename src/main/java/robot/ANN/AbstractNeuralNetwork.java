package robot.ANN;

import java.util.ArrayList;

public abstract class AbstractNeuralNetwork {
    protected ArrayList<InputNeuron> input;
    protected ArrayList<OutputNeuron> output;
    protected ArrayList<Neuron> neurons;

    /**
     * update evaluates the current state of the neural network and fires the
     * output events accordingly.
     */
    public final void update() {
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
    public final OutputNeuron addOutputToNetwork(IOutput output,
        AbstractEvaluationFunction function) {
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
    protected final Neuron addNeuronToNetwork(AbstractEvaluationFunction function) {
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

}
