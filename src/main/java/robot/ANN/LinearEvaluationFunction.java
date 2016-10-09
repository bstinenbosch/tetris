package robot.ANN;

import java.util.ArrayList;

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
