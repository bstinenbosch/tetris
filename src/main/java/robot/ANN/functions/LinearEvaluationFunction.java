package robot.ANN.functions;

import java.util.ArrayList;

import robot.ANN.Neuron.AbstractNeuron;

public class LinearEvaluationFunction extends AbstractEvaluationFunction {

    @Override
    public double evaluate(ArrayList<AbstractNeuron> neurons) {
        double value = 0;
        for (AbstractNeuron neuron : neurons) {
            value += weights.get(neuron) * neuron.getCharge();
        }
        return value / neurons.size();
    }

}
