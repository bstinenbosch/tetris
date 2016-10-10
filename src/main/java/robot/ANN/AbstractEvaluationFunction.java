package robot.ANN;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractEvaluationFunction {
    protected HashMap<AbstractNeuron, Double> weights;

    public AbstractEvaluationFunction() {
        weights = new HashMap<AbstractNeuron, Double>();
    }

    /**
     * evaluate determines what charge is passed to the Neuron on the other
     * side.
     * 
     * @param neurons
     *            the neurons whose charge must be evaluated.
     * @return the output charge. Should at all times be in [0,1].
     */
    public abstract double evaluate(ArrayList<AbstractNeuron> neurons);

    public final void update(AbstractNeuron neuron, double weight) {
        weights.put(neuron, weight);
    }

    public final double getWeight(AbstractNeuron neuron) {
        return weights.getOrDefault(neuron, 0.);
    }
}
