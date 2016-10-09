package robot.ANN;

import java.util.ArrayList;

public class Neuron extends AbstractNeuron {
    private ArrayList<AbstractNeuron> input;
    private AbstractEvaluationFunction function;

    public Neuron(ArrayList<AbstractNeuron> input, AbstractEvaluationFunction function) {
        this.function = function;
        this.input = input;
    }

    public Neuron(AbstractEvaluationFunction function) {
        this(new ArrayList<AbstractNeuron>(), function);
    }

    @Override
    public double getCharge() {
        if (charge == -1) {
            charge = function.evaluate(input);
        }
        return charge;
    }

    @Override
    public void uncharge() {
        for (AbstractNeuron neuron : input) {
            neuron.uncharge();
        }
        charge = -1;
    }

    public void add(AbstractNeuron neuron, double weight) {
        this.input.add(neuron);
        this.function.update(neuron, weight);
    }

    @Override
    public boolean hasAsPredecessor(AbstractNeuron neuron) {
        for (AbstractNeuron predecessor : input) {
            if (predecessor.equals(neuron) || predecessor.hasAsPredecessor(neuron)) {
                return true;
            }
        }
        return false;
    }
}
