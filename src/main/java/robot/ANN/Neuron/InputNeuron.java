package robot.ANN.Neuron;

import java.util.Iterator;
import java.util.LinkedList;

import robot.ANN.NeuralPathNode;

public class InputNeuron extends AbstractNeuron {
    private IInput input;

    @Override
    public Iterator<LinkedList<NeuralPathNode>> iterator(LinkedList<NeuralPathNode> path) {
        return new InputIterator(path);
    }

    public InputNeuron(IInput input) {
        this.input = input;
    }

    @Override
    public void uncharge() {
        charge = -1;
    }

    @Override
    public boolean hasAsPredecessor(AbstractNeuron neuron) {
        return false;
    }

    @Override
    public double getCharge() {
        return input.processInput();
    }

    public IInput getInput() {
        return input;
    }

}
