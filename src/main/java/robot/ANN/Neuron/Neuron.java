package robot.ANN.Neuron;

import java.util.HashMap;
import java.util.Iterator;

import robot.ANN.Path;
import robot.ANN.functions.EvaluationFunction;
import robot.ANN.functions.IEvaluationFunction;

public class Neuron extends AbstractNeuron {
    private HashMap<AbstractNeuron, Double> predecessors;
    private IEvaluationFunction function;

    public Iterator<Path> iterator(Path path) {
        return new NeuronIterator(path, predecessors.entrySet().iterator());
    }

    public Neuron(EvaluationFunction function) {
        this.predecessors = new HashMap<AbstractNeuron, Double>();
        this.function = function.newInstance();
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
        for (AbstractNeuron neuron : predecessors.keySet()) {
            neuron.uncharge();
        }
        charge = -1;
    }

    public void add(AbstractNeuron neuron, double weight) {
        this.predecessors.put(neuron, weight);
    }

    @Override
    public boolean hasAsPredecessor(AbstractNeuron neuron) {
        if (this.equals(neuron)) {
            return true;
        }
        for (AbstractNeuron predecessor : predecessors.keySet()) {
            if (predecessor.hasAsPredecessor(neuron)) {
                return true;
            }
        }
        return false;
    }
}
