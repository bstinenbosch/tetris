package robot.ANN.functions;

import java.util.HashMap;
import java.util.Map.Entry;

import robot.ANN.Neuron.AbstractNeuron;

public class SigmoidEvaluationFunction implements IEvaluationFunction {
    @Override
    public double evaluate(HashMap<AbstractNeuron, Double> predecessors) {
        double value = 0;
        for (Entry<AbstractNeuron, Double> entry : predecessors.entrySet()) {
            value += entry.getValue() * entry.getKey().getCharge();
        }
        return 1 / (1 + Math.exp(value));
    }

}
