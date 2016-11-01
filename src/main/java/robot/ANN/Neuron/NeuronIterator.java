package robot.ANN.Neuron;

import java.util.Iterator;
import java.util.Map.Entry;

import robot.ANN.PathNode;
import robot.ANN.Path;

public class NeuronIterator implements Iterator<Path> {
    private Iterator<Entry<AbstractNeuron, Double>> predecessorIterator;
    private Iterator<Path> currentPredecessorPathIterator;
    private Path path;

    public NeuronIterator(Path path, Iterator<Entry<AbstractNeuron, Double>> iterator) {
        this.path = path;
        this.predecessorIterator = iterator;
        if (iterator.hasNext()) {
            Entry<AbstractNeuron, Double> currentPredecessor = iterator.next();
            this.path.push(
                new PathNode(currentPredecessor.getValue(), currentPredecessor.getKey()));
            currentPredecessorPathIterator = currentPredecessor.getKey().iterator(this.path);
        }
    }

    @Override
    public boolean hasNext() {
        if (currentPredecessorPathIterator == null) {
            return false;// init must have failed. nothing to pop.
        } // if current iterator is empty, try find a new one amongst
          // children
        while (!currentPredecessorPathIterator.hasNext()) {
            if (predecessorIterator.hasNext()) {
                Entry<AbstractNeuron, Double> currentPredecessor = predecessorIterator.next();
                path.pop();
                path.push(
                    new PathNode(currentPredecessor.getValue(), currentPredecessor.getKey()));
                currentPredecessorPathIterator = currentPredecessor.getKey().iterator(path);
            } else {
                path.pop();
                return false;
            }
        }
        return currentPredecessorPathIterator.hasNext();
    }

    @Override
    public Path next() {
        // by virtue of hasNext, the currentPredecessorIterator is loaded
        // with an iterator which 'hasNext'.
        return currentPredecessorPathIterator.next();
        // TODO return the next from the child after updating weight
        // accordingly
    }

}