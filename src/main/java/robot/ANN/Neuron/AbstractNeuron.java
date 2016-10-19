package robot.ANN.Neuron;

import java.util.Iterator;
import java.util.LinkedList;

import robot.ANN.NeuralPathNode;

/**
 * AbstractNeuron is the abstract class from which all nodes in the neural
 * network should be derived. This abstract class supplies an interface that
 * allows to recur through the neural network. This implementation allows for a
 * wide variety of signal processing choices.
 */
public abstract class AbstractNeuron {
    /**
     * charge is used to buffer calculation results when calculating the output
     * of the network.
     */
    protected double charge = -1;

    /**
     * getCharge returns how much this neuron is charged by other neurons/input.
     * 
     * @return the charge of this neuron.
     */
    public abstract double getCharge();

    /**
     * uncharge is used to unset the charge of a neuron when a new state is
     * entered.
     */
    public abstract void uncharge();

    /**
     * check if the supplied neuron is in the predecessors of this neuron.
     * 
     * @param neuron
     * @return true if it is, else false.
     */
    public abstract boolean hasAsPredecessor(AbstractNeuron neuron);

    public abstract Iterator<LinkedList<NeuralPathNode>> iterator(LinkedList<NeuralPathNode> path);
}
