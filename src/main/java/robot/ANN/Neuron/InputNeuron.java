package robot.ANN.Neuron;

import java.util.Iterator;
import java.util.LinkedList;

import robot.ANN.NeuralPathNode;

public class InputNeuron extends AbstractNeuron {
    private IInput input;

    private class InputIterator implements Iterator<LinkedList<NeuralPathNode>> {

        private LinkedList<NeuralPathNode> path;
        private boolean hasIterated = false;

        public InputIterator(LinkedList<NeuralPathNode> path) {
            this.path = path;
        }

        @Override
        public boolean hasNext() {
            return (!hasIterated);
        }

        @SuppressWarnings("unchecked")
        @Override
        public LinkedList<NeuralPathNode> next() {
            hasIterated = true;
            return (LinkedList<NeuralPathNode>) path.clone();
        }
    }

    @Override
    public Iterator<LinkedList<NeuralPathNode>> iterator(LinkedList<NeuralPathNode> path) {
        return new InputIterator(path);
    }

    public InputNeuron(IInput input) {
        this.input = input;
    }

    public void uncharge() {
        charge = -1;
    }

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
