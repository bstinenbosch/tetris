package robot.ANN.Neuron;

import java.util.Iterator;
import java.util.LinkedList;

import robot.ANN.NeuralPathNode;

public class InputIterator implements Iterator<LinkedList<NeuralPathNode>> {

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
