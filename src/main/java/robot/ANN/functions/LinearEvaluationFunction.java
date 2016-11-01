package robot.ANN.functions;

import java.util.HashMap;
import java.util.Map.Entry;

import robot.ANN.Neuron.AbstractNeuron;

public class LinearEvaluationFunction implements IEvaluationFunction {

    @Override
    public double evaluate(HashMap<AbstractNeuron, Double> predecessors) {
        double value = 0;
        double sum = 0;
        for (Entry<AbstractNeuron, Double> entry : predecessors.entrySet()) {
            value += entry.getValue() * entry.getKey().getCharge();
            sum += entry.getValue();
        }
        return value / sum;
    }

}
