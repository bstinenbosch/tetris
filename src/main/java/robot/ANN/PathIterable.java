package robot.ANN;

import java.util.Iterator;
import java.util.LinkedList;

import robot.ANN.Neuron.OutputNeuron;

public class PathIterable implements Iterable<LinkedList<NeuralPathNode>> {
    private class PathIterator implements Iterator<LinkedList<NeuralPathNode>> {
        private Iterator<OutputNeuron> outputIterator;
        private Iterator<LinkedList<NeuralPathNode>> currentNeuronIterator;
        private LinkedList<NeuralPathNode> path;

        public PathIterator(Iterator<OutputNeuron> outputIterator) {
            this.outputIterator = outputIterator;
            if (outputIterator.hasNext()) {
                OutputNeuron oNeuron = outputIterator.next();
                path = new LinkedList<NeuralPathNode>();
                path.push(new NeuralPathNode(0, oNeuron));
                currentNeuronIterator = oNeuron.iterator(path);
            }
        }

        @Override
        public boolean hasNext() {
            if (currentNeuronIterator == null) {
                return false;
            }
            while (!currentNeuronIterator.hasNext()) {
                if (outputIterator.hasNext()) {
                    OutputNeuron oNeuron = outputIterator.next();
                    path = new LinkedList<NeuralPathNode>();
                    path.push(new NeuralPathNode(0, oNeuron));
                    currentNeuronIterator = oNeuron.iterator(path);
                } else {
                    return false;
                }
            }
            return currentNeuronIterator.hasNext();
        }

        @Override
        public LinkedList<NeuralPathNode> next() {
            return currentNeuronIterator.next();
        }
    }

    private Iterator<OutputNeuron> outputIterator;

    public PathIterable(Iterator<OutputNeuron> outputIterator) {
        this.outputIterator = outputIterator;
    }

    @Override
    public Iterator<LinkedList<NeuralPathNode>> iterator() {
        return new PathIterator(outputIterator);
    }
}
