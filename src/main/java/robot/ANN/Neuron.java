package robot.ANN;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Neuron extends AbstractNeuron {
    private ArrayList<AbstractNeuron> predecessors;
    private AbstractEvaluationFunction function;

    private class NeuronIterator implements Iterator<LinkedList<NeuralPathNode>> {
        private Iterator<AbstractNeuron> predecessorIterator = predecessors.iterator();
        private Iterator<LinkedList<NeuralPathNode>> currentPredecessorPathIterator;
        private LinkedList<NeuralPathNode> path;

        public NeuronIterator(LinkedList<NeuralPathNode> path) {
            this.path = path;
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
                    path.push(new NeuralPathNode(function.getWeight(currentPredecessor),
                        currentPredecessor));
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

    public Iterator<LinkedList<NeuralPathNode>> iterator(LinkedList<NeuralPathNode> path) {
        return new NeuronIterator(path);
    }

    public Neuron(ArrayList<AbstractNeuron> input, AbstractEvaluationFunction function) {
        this.function = function;
        this.predecessors = input;
    }

    public Neuron(AbstractEvaluationFunction function) {
        this(new ArrayList<AbstractNeuron>(), function);
    }

    @Override
    public double getCharge() {
        if (charge == -1) {
            charge = function.evaluate(predecessors);
        }
        return charge;
    }

    @Override
    public void uncharge() {
        for (AbstractNeuron neuron : predecessors) {
            neuron.uncharge();
        }
        charge = -1;
    }

    public void add(AbstractNeuron neuron, double weight) {
        this.predecessors.add(neuron);
        this.function.update(neuron, weight);
    }

    @Override
    public boolean hasAsPredecessor(AbstractNeuron neuron) {
        for (AbstractNeuron predecessor : predecessors) {
            if (predecessor.equals(neuron) || predecessor.hasAsPredecessor(neuron)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<AbstractNeuron> getPredecessors() {
        return predecessors;
    }

    public AbstractEvaluationFunction getFunction() {
        return function;
    }
}
