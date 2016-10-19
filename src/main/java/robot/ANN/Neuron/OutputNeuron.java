package robot.ANN.Neuron;

import robot.ANN.functions.AbstractEvaluationFunction;

public class OutputNeuron extends Neuron {
    private IOutput output;

    public OutputNeuron(AbstractEvaluationFunction function, IOutput output) {
        super(function);
        this.output = output;
    }

    @Override
    public double getCharge() {
        double charge = super.getCharge();
        output.interpret(charge);
        return charge;
    }

    public IOutput getOutput() {
        return output;
    }
}
