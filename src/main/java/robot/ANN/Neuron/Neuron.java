package robot.ANN.Neuron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import robot.ANN.NeuralPathNode;
import robot.ANN.functions.AbstractEvaluationFunction;
import robot.ANN.functions.EvaluationFunction;

public class Neuron extends AbstractNeuron {
    private ArrayList<AbstractNeuron> predecessors;
    private AbstractEvaluationFunction function;

    public Iterator<LinkedList<NeuralPathNode>> iterator(LinkedList<NeuralPathNode> path) {
        return new NeuronIterator(path, predecessors.iterator(), function);
    }

    public Neuron(ArrayList<AbstractNeuron> input, EvaluationFunction function) {
        this.function = function.newInstance();
        this.predecessors = input;
    }

    public Neuron(EvaluationFunction function) {
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
