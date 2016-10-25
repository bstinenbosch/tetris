package robot.ANN.Neuron;

import java.util.Iterator;
import java.util.LinkedList;

import robot.ANN.NeuralPathNode;
import robot.ANN.functions.AbstractEvaluationFunction;

public class NeuronIterator implements Iterator<LinkedList<NeuralPathNode>> {
    private Iterator<AbstractNeuron> predecessorIterator;
    private Iterator<LinkedList<NeuralPathNode>> currentPredecessorPathIterator;
    private LinkedList<NeuralPathNode> path;
    private AbstractEvaluationFunction function;

    public NeuronIterator(LinkedList<NeuralPathNode> path,
        Iterator<AbstractNeuron> predecessorIterator, AbstractEvaluationFunction function) {
        this.path = path;
        this.predecessorIterator = predecessorIterator;
        this.function = function;
        if (predecessorIterator.hasNext()) {
            AbstractNeuron currentPredecessor = (AbstractNeuron) predecessorIterator.next();
            this.path.push(
                new NeuralPathNode(function.getWeight(currentPredecessor), currentPredecessor));
            currentPredecessorPathIterator = currentPredecessor.iterator(this.path);
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
                AbstractNeuron currentPredecessor = (AbstractNeuron) predecessorIterator.next();
                path.pop();
                path.push(
                    new NeuralPathNode(function.getWeight(currentPredecessor), currentPredecessor));
                currentPredecessorPathIterator = currentPredecessor.iterator(path);
            } else {
                path.pop();
                return false;
            }
        }
        return currentPredecessorPathIterator.hasNext();
    }

    @Override
    public LinkedList<NeuralPathNode> next() {
        // by virtue of hasNext, the currentPredecessorIterator is loaded
        // with an iterator which 'hasNext'.
        return currentPredecessorPathIterator.next();
        // TODO return the next from the child after updating weight
        // accordingly
    }

}